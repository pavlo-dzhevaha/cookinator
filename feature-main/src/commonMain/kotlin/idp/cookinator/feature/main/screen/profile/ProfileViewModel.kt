package idp.cookinator.feature.main.screen.profile

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.recipe.DeleteUserRecipeUseCase
import idp.cookinator.domain.recipe.ObserveUserRecipesUseCase
import idp.cookinator.feature.main.screen.profile.contract.ProfileEvent
import idp.cookinator.feature.main.screen.profile.contract.ProfileIntent
import idp.cookinator.feature.main.screen.profile.contract.ProfileRecipeItem
import idp.cookinator.feature.main.screen.profile.contract.ProfileState
import idp.cookinator.model.UserRecipe
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

internal class ProfileViewModel(
    private val observeUserRecipes: ObserveUserRecipesUseCase,
    private val deleteUserRecipe: DeleteUserRecipeUseCase,
) : MviViewModel<ProfileState, ProfileIntent, ProfileEvent>(ProfileState.initialState) {

    private var observeJob: Job? = null

    init {
        observeRecipes()
    }

    override fun onIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.OnRecipeClick ->
                sendEvent(ProfileEvent.NavigateToUserRecipe(intent.item.userRecipeId))
            is ProfileIntent.OnDeleteRecipe ->
                updateState { it.copy(deleteConfirmation = intent.item) }
            ProfileIntent.OnConfirmDeleteRecipe -> confirmDeleteRecipe()
            ProfileIntent.OnDismissDeleteDialog ->
                updateState { it.copy(deleteConfirmation = null) }
            is ProfileIntent.OnEditRecipe ->
                sendEvent(ProfileEvent.NavigateToEditUserRecipe(intent.item.userRecipeId))
            ProfileIntent.OnRetry -> observeRecipes()
        }
    }

    private fun confirmDeleteRecipe() {
        val item = state.deleteConfirmation ?: return
        updateState { it.copy(deleteConfirmation = null) }
        launch {
            deleteUserRecipe(item.userRecipeId)
                .onFailure { e ->
                    logger.e(e) { "Failed to delete user recipe ${item.userRecipeId}" }
                }
        }
    }

    private fun observeRecipes() {
        observeJob?.cancel()
        observeJob = launch {
            observeUserRecipes()
                .onStart { updateState { it.copy(uiState = UiState.LOADING) } }
                .catch { e ->
                    logger.e(e) { "Failed to observe user recipes" }
                    updateState { it.copy(uiState = UiState.ERROR) }
                }
                .collectLatest { recipes ->
                    val items = recipes.map { it.toProfileItem() }
                    updateState {
                        it.copy(
                            uiState = if (items.isEmpty()) UiState.EMPTY else UiState.SUCCESS,
                            items = items,
                        )
                    }
                }
        }
    }

    private fun UserRecipe.toProfileItem(): ProfileRecipeItem = ProfileRecipeItem(
        userRecipeId = id,
        model = RecipeUiModel(
            recipe = toRecipe(),
            isSaved = false,
        ),
    )
}
