package idp.cookinator.feature.main.screen.home

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.GetRandomRecipesUseCase
import idp.cookinator.domain.recipe.ObserveDiscoveryRecipesUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.feature.main.screen.home.contract.HomeEvent
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState
import idp.cookinator.coreui.model.RecipeUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine

internal class HomeViewModel(
    private val getRandomRecipes: GetRandomRecipesUseCase,
    private val observeDiscoveryRecipes: ObserveDiscoveryRecipesUseCase,
    private val observeLikedRecipeIds: ObserveLikedRecipeIdsUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
) : MviViewModel<HomeState, HomeIntent, HomeEvent>(HomeState.initialState) {

    init {
        observeAndCombineData()
        fetchData()
    }

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnFetchRecipe -> fetchData()
            is HomeIntent.OnSearchQueryChange -> onChangeSearchQuery(intent.query)
            is HomeIntent.OnToggleSaved -> onToggleSaved(intent.model)
            is HomeIntent.OnRecipeClick -> onRecipeClick(intent.model)
            is HomeIntent.OnSeeAllClick -> sendEvent(HomeEvent.NavigateToAllRecipes(intent.section))
        }
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

    private fun fetchData() = launch {
        updateState { it.copy(uiState = UiState.LOADING) }
        getRandomRecipes(forceRefresh = true)
            .onSuccess { list ->
                if (list.isEmpty()) {
                    updateState { it.copy(uiState = UiState.EMPTY) }
                    return@launch
                }
            }.onFailure { e ->
                logger.e(e) { "Failed to fetch random recipes" }
                updateState { it.copy(uiState = UiState.ERROR) }
            }
    }

    private fun observeAndCombineData() = launch {
        combine(
            observeDiscoveryRecipes(),
            observeLikedRecipeIds(),
        ) { recipes, savedIds ->
            recipes.map { recipe ->
                RecipeUiModel(
                    recipe = recipe,
                    isSaved = savedIds.contains(recipe.id),
                )
            }
        }.collectLatest { combinedUiList ->
            if (combinedUiList.isNotEmpty()) {
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        trending = combinedUiList.take(TRENDING_PREVIEW_COUNT),
                    )
                }
            }
        }
    }

    private companion object {
        const val TRENDING_PREVIEW_COUNT = 10
    }
}
