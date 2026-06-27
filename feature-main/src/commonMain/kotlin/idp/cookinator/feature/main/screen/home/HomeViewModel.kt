package idp.cookinator.feature.main.screen.home

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.GetRandomRecipesUseCase
import idp.cookinator.domain.recipe.ObserveLikedRecipeIdsUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.feature.main.screen.home.contract.HomeEvent
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine

internal class HomeViewModel(
    private val getRandomRecipes: GetRandomRecipesUseCase,
    private val observeLikedRecipeIds: ObserveLikedRecipeIdsUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
) : MviViewModel<HomeState, HomeIntent, HomeEvent>(HomeState.initialState) {

    private val rawRecipes = MutableStateFlow<List<Recipe>>(emptyList())

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
        getRandomRecipes()
            .onSuccess { list ->
                if (list.isEmpty()) {
                    updateState { it.copy(uiState = UiState.EMPTY) }
                    return@launch
                }
                rawRecipes.value = list
            }.onFailure {
                updateState { it.copy(uiState = UiState.ERROR) }
            }
    }

    private fun observeAndCombineData() = launch {
        combine(
            rawRecipes,
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
                        trending = combinedUiList.take(10),
                    )
                }
            }
        }
    }
}
