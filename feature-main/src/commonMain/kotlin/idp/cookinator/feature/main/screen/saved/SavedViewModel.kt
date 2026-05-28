package idp.cookinator.feature.main.screen.saved

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.DomainManager
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel
import idp.cookinator.feature.main.screen.saved.contract.SavedEvent
import idp.cookinator.feature.main.screen.saved.contract.SavedIntent
import idp.cookinator.feature.main.screen.saved.contract.SavedState
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine

internal class SavedViewModel(
    private val domain: DomainManager,
) : MviViewModel<SavedState, SavedIntent, SavedEvent>(SavedState.initialState) {

    private val rawRecipes = MutableStateFlow<List<Recipe>>(emptyList())

    init {
        observeAndCombineData()
        fetchData()
    }

    override fun onIntent(intent: SavedIntent) {
        when (intent) {
            is SavedIntent.OnToggleSaved -> onToggleSaved(intent.model)
            SavedIntent.OnRetry -> fetchData()
        }
    }

    private fun onToggleSaved(model: RecipeUiModel) = launch {
        domain.setRecipeLiked(
            recipeId = model.recipe.id,
            isLiked = !model.isSaved,
        )
    }

    private fun fetchData() = launch {
        updateState { it.copy(uiState = UiState.LOADING) }
        domain
            .getRandomRecipes()
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
            domain.likedRecipeIds
        ) { recipes, savedIds ->
            // Map the raw recipes into UI models
            recipes
                .map { recipe ->
                    RecipeUiModel(
                        recipe = recipe,
                        isSaved = savedIds.contains(recipe.id)
                    )
                }.filter(RecipeUiModel::isSaved)
        }.collectLatest { combinedUiList ->
            if (combinedUiList.isEmpty()) {
                updateState { it.copy(uiState = UiState.EMPTY) }
            } else {
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        items = combinedUiList,
                    )
                }
            }
        }
    }
}
