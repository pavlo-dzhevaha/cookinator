package idp.cookinator.feature.main.screen.home

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.SyncDiscoveryRecipesUseCase
import idp.cookinator.domain.recipe.ObserveDiscoveryRecipesUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.ObserveRecentlyViewedUseCase
import idp.cookinator.domain.recipe.ObserveRecipeDishTypesUseCase
import idp.cookinator.domain.recipe.ObserveRecipesByDishTypeUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.feature.main.screen.home.contract.HomeEvent
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState
import idp.cookinator.feature.navigation.features.AllRecipesFilter
import idp.cookinator.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModel(
    private val syncDiscoveryRecipes: SyncDiscoveryRecipesUseCase,
    private val observeDiscoveryRecipes: ObserveDiscoveryRecipesUseCase,
    private val observeRecipeDishTypes: ObserveRecipeDishTypesUseCase,
    private val observeRecipesByDishType: ObserveRecipesByDishTypeUseCase,
    private val observeLikedRecipeIds: ObserveLikedRecipeIdsUseCase,
    private val observeRecentlyViewed: ObserveRecentlyViewedUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
) : MviViewModel<HomeState, HomeIntent, HomeEvent>(HomeState.initialState) {

    private val selectedDishType = MutableStateFlow<String?>(null)

    init {
        observeAndCombineData()
        observeRecentlyViewedData()
        fetchData()
    }

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnFetchRecipe -> fetchData()
            is HomeIntent.OnSearchQueryChange -> onChangeSearchQuery(intent.query)
            is HomeIntent.OnToggleSaved -> onToggleSaved(intent.model)
            is HomeIntent.OnRecipeClick -> onRecipeClick(intent.model)
            is HomeIntent.OnSeeAllClick -> sendEvent(HomeEvent.NavigateToAllRecipes(intent.filter))
            is HomeIntent.OnCategorySelected -> onCategorySelected(intent.category)
            is HomeIntent.OnPopularCategorySeeAllClick -> sendEvent(
                HomeEvent.NavigateToAllRecipes(AllRecipesFilter.Category(intent.category)),
            )
            is HomeIntent.OnCustomizeRecipe ->
                sendEvent(HomeEvent.NavigateToCustomizeRecipe(intent.model.recipe.id))
        }
    }

    private fun onCategorySelected(category: String) {
        selectedDishType.value = category
        updateState { it.copy(selectedCategory = category) }
    }

    private fun onRecipeClick(model: RecipeUiModel) {
        sendEvent(HomeEvent.NavigateToRecipe(model.recipe.id))
    }

    private fun onChangeSearchQuery(query: String) {
        updateState { it.copy(query = query) }
    }

    private fun onToggleSaved(model: RecipeUiModel) = launch {
        setRecipeLiked(
            recipeId = model.recipe.id,
            isLiked = !model.isSaved,
        )
    }

    private fun fetchData(forceRefresh: Boolean = true) = launch {
        val hasCachedData = observeDiscoveryRecipes().first().isNotEmpty()
        if (!hasCachedData) {
            updateState { it.copy(uiState = UiState.LOADING) }
        }
        syncDiscoveryRecipes(forceRefresh = forceRefresh)
            .onFailure { e ->
                logger.e(e) { "Failed to sync discovery recipes" }
                if (observeDiscoveryRecipes().first().isEmpty()) {
                    updateState { it.copy(uiState = UiState.ERROR) }
                }
            }
    }

    private fun observeAndCombineData() = launch {
        combine(
            observeDiscoveryRecipes(),
            observeRecipeDishTypes(),
            selectedDishType,
            observeLikedRecipeIds(),
        ) { discoveryRecipes, categories, selected, savedIds ->
            val selectedCategory = selected
                ?.takeIf { it in categories }
                ?: categories.firstOrNull()
            HomeDataSlice(
                discoveryRecipes = discoveryRecipes,
                categories = categories,
                selectedCategory = selectedCategory,
                savedIds = savedIds,
            )
        }
            .flatMapLatest { slice ->
                val category = slice.selectedCategory
                if (category == null) {
                    flowOf(slice to emptyList())
                } else {
                    observeRecipesByDishType(category).map { recipes ->
                        slice to recipes
                    }
                }
            }
            .collectLatest { (slice, popularRecipes) ->
                if (slice.discoveryRecipes.isEmpty()) {
                    updateState { state ->
                        if (state.uiState == UiState.SUCCESS) {
                            state
                        } else {
                            state.copy(
                                uiState = when (state.uiState) {
                                    UiState.ERROR -> UiState.ERROR
                                    UiState.LOADING -> UiState.LOADING
                                    else -> UiState.EMPTY
                                },
                                trending = emptyList(),
                            )
                        }
                    }
                    return@collectLatest
                }

                val savedIds = slice.savedIds
                val trending = slice.discoveryRecipes
                    .take(TRENDING_PREVIEW_COUNT)
                    .map { recipe ->
                        RecipeUiModel(
                            recipe = recipe,
                            isSaved = savedIds.contains(recipe.id),
                        )
                    }
                val popular = popularRecipes.map { recipe ->
                    RecipeUiModel(
                        recipe = recipe,
                        isSaved = savedIds.contains(recipe.id),
                    )
                }
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        trending = trending,
                        categories = slice.categories,
                        selectedCategory = slice.selectedCategory,
                        popularRecipes = popular.take(POPULAR_PREVIEW_COUNT),
                        showPopularSeeAll = popular.size > POPULAR_PREVIEW_COUNT,
                    )
                }
            }
    }

    private fun observeRecentlyViewedData() = launch {
        observeRecentlyViewed(limit = RECENTLY_VIEWED_PREVIEW_COUNT)
            .combine(observeLikedRecipeIds()) { recipes, savedIds ->
                recipes.map { recipe ->
                    RecipeUiModel(
                        recipe = recipe,
                        isSaved = savedIds.contains(recipe.id),
                    )
                }
            }
            .catch { e ->
                logger.e(e) { "Failed to observe recently viewed recipes" }
            }
            .collectLatest { items ->
                updateState { it.copy(recentlyViewed = items) }
            }
    }

    private data class HomeDataSlice(
        val discoveryRecipes: List<Recipe>,
        val categories: List<String>,
        val selectedCategory: String?,
        val savedIds: List<Int>,
    )

    private companion object {
        const val TRENDING_PREVIEW_COUNT = 10
        const val POPULAR_PREVIEW_COUNT = 10
        const val RECENTLY_VIEWED_PREVIEW_COUNT = 10
    }
}
