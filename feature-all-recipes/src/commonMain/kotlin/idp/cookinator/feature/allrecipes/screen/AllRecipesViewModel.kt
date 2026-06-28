package idp.cookinator.feature.allrecipes.screen

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.GetRandomRecipesUseCase
import idp.cookinator.domain.recipe.ObserveDiscoveryRecipesUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.ObserveRecentlyViewedUseCase
import idp.cookinator.domain.recipe.ObserveRecipesByDishTypeUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesEvent
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesIntent
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesState
import idp.cookinator.feature.navigation.features.AllRecipesFilter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

internal class AllRecipesViewModel(
    private val filter: AllRecipesFilter,
    private val getRandomRecipes: GetRandomRecipesUseCase,
    private val observeDiscoveryRecipes: ObserveDiscoveryRecipesUseCase,
    private val observeRecipesByDishType: ObserveRecipesByDishTypeUseCase,
    private val observeRecentlyViewed: ObserveRecentlyViewedUseCase,
    private val observeLikedRecipeIds: ObserveLikedRecipeIdsUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
) : MviViewModel<AllRecipesState, AllRecipesIntent, AllRecipesEvent>(AllRecipesState.initialState) {

    init {
        observeAndCombineData()
        ensureDataLoaded()
    }

    override fun onIntent(intent: AllRecipesIntent) {
        when (intent) {
            is AllRecipesIntent.OnRecipeClick -> sendEvent(AllRecipesEvent.NavigateToRecipe(intent.model.recipe.id))
            is AllRecipesIntent.OnToggleSaved -> onToggleSaved(intent.model)
            AllRecipesIntent.OnRetry -> fetchData(forceRefresh = true)
            AllRecipesIntent.OnBack -> sendEvent(AllRecipesEvent.NavigateBack)
        }
    }

    private fun onToggleSaved(model: RecipeUiModel) = launch {
        setRecipeLiked(
            recipeId = model.recipe.id,
            isLiked = !model.isSaved,
        )
    }

    private fun ensureDataLoaded() = launch {
        if (filter is AllRecipesFilter.RecentlyViewed) return@launch
        if (observeDiscoveryRecipes().first().isEmpty()) {
            fetchData(forceRefresh = false)
        }
    }

    private fun fetchData(forceRefresh: Boolean) = launch {
        updateState { it.copy(uiState = UiState.LOADING) }
        getRandomRecipes(forceRefresh = forceRefresh)
            .onSuccess { list ->
                if (list.isEmpty()) {
                    updateState { it.copy(uiState = UiState.EMPTY) }
                }
            }
            .onFailure { e ->
                logger.e(e) { "Failed to fetch discovery recipes" }
                updateState { it.copy(uiState = UiState.ERROR) }
            }
    }

    private fun observeAndCombineData() = launch {
        observeRecipes(filter)
            .combine(observeLikedRecipeIds()) { recipes, savedIds ->
                recipes.map { recipe ->
                    RecipeUiModel(
                        recipe = recipe,
                        isSaved = savedIds.contains(recipe.id),
                    )
                }
            }
            .catch { e ->
                logger.e(e) { "Failed to observe recipes for filter=$filter" }
                updateState { it.copy(uiState = UiState.ERROR) }
            }
            .collectLatest { items ->
                updateState {
                    it.copy(
                        uiState = when {
                            items.isNotEmpty() -> UiState.SUCCESS
                            filter is AllRecipesFilter.RecentlyViewed -> UiState.EMPTY
                            else -> it.uiState
                        },
                        items = items,
                    )
                }
            }
    }

    private fun observeRecipes(filter: AllRecipesFilter) = when (filter) {
        AllRecipesFilter.Trending -> observeDiscoveryRecipes()
        is AllRecipesFilter.Category -> observeRecipesByDishType(filter.dishType)
        AllRecipesFilter.RecentlyViewed -> observeRecentlyViewed()
    }
}
