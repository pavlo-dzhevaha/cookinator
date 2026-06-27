package idp.cookinator.feature.main.screen.saved

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.DomainManager
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel
import idp.cookinator.feature.main.screen.saved.contract.SavedEvent
import idp.cookinator.feature.main.screen.saved.contract.SavedIntent
import idp.cookinator.feature.main.screen.saved.contract.SavedState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

internal class SavedViewModel(
    private val domain: DomainManager,
) : MviViewModel<SavedState, SavedIntent, SavedEvent>(SavedState.initialState) {

    private var observeJob: Job? = null

    init {
        observeLikedRecipes()
    }

    override fun onIntent(intent: SavedIntent) {
        when (intent) {
            is SavedIntent.OnToggleSaved -> onToggleSaved(intent.model)
            SavedIntent.OnRetry -> observeLikedRecipes()
        }
    }

    private fun onToggleSaved(model: RecipeUiModel) = launch {
        domain.setRecipeLiked(
            recipeId = model.recipe.id,
            isLiked = !model.isSaved,
        )
    }

    private fun observeLikedRecipes() {
        observeJob?.cancel()
        observeJob = launch {
            domain.likedRecipes
                .onStart { updateState { it.copy(uiState = UiState.LOADING) } }
                .catch { updateState { it.copy(uiState = UiState.ERROR) } }
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
