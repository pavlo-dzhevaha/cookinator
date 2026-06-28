package idp.cookinator.feature.main.screen.saved

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.ObserveLikedRecipesUseCase
import idp.cookinator.domain.recipe.SetRecipeLikedUseCase
import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.feature.main.screen.saved.contract.SavedEvent
import idp.cookinator.feature.main.screen.saved.contract.SavedIntent
import idp.cookinator.feature.main.screen.saved.contract.SavedState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

internal class SavedViewModel(
    private val observeLikedRecipes: ObserveLikedRecipesUseCase,
    private val setRecipeLiked: SetRecipeLikedUseCase,
) : MviViewModel<SavedState, SavedIntent, SavedEvent>(SavedState.initialState) {

    private var observeJob: Job? = null

    init {
        observeLikedRecipesList()
    }

    override fun onIntent(intent: SavedIntent) {
        when (intent) {
            is SavedIntent.OnToggleSaved -> onToggleSaved(intent.model)
            is SavedIntent.OnRecipeClick -> onRecipeClick(intent.model)
            is SavedIntent.OnCustomizeRecipe -> onCustomizeRecipe(intent.model)
            SavedIntent.OnRetry -> observeLikedRecipesList()
        }
    }

    private fun onCustomizeRecipe(model: RecipeUiModel) {
        sendEvent(SavedEvent.NavigateToCustomizeRecipe(model.recipe.id))
    }

    private fun onRecipeClick(model: RecipeUiModel) {
        sendEvent(SavedEvent.NavigateToRecipe(model.recipe.id))
    }

    private fun onToggleSaved(model: RecipeUiModel) = launch {
        setRecipeLiked(
            recipeId = model.recipe.id,
            isLiked = !model.isSaved,
        )
    }

    private fun observeLikedRecipesList() {
        observeJob?.cancel()
        observeJob = launch {
            observeLikedRecipes()
                .onStart { updateState { it.copy(uiState = UiState.LOADING) } }
                .catch { e ->
                    logger.e(e) { "Failed to observe liked recipes" }
                    updateState { it.copy(uiState = UiState.ERROR) }
                }
                .collectLatest { recipes ->
                    val items = recipes.map { recipe ->
                        RecipeUiModel(recipe = recipe, isSaved = true)
                    }
                    updateState {
                        it.copy(
                            uiState = if (items.isEmpty()) UiState.EMPTY else UiState.SUCCESS,
                            items = items,
                        )
                    }
                }
        }
    }
}
