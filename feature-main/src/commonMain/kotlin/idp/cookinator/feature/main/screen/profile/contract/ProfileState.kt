package idp.cookinator.feature.main.screen.profile.contract

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseEvent
import idp.cookinator.coreui.viewmodel.base.BaseState

internal data class ProfileState(
    val uiState: UiState,
    val items: List<ProfileRecipeItem>,
    val deleteConfirmation: ProfileRecipeItem? = null,
) : BaseState {
    companion object {
        val initialState = ProfileState(
            uiState = UiState.LOADING,
            items = emptyList(),
        )
    }
}

internal data class ProfileRecipeItem(
    val userRecipeId: Long,
    val model: RecipeUiModel,
) {
    companion object {
        val stub = ProfileRecipeItem(
            userRecipeId = 0,
            model = RecipeUiModel.stub,
        )

        val stubs = RecipeUiModel.stubs.mapIndexed { index, model ->
            ProfileRecipeItem(
                userRecipeId = index.toLong(),
                model = model,
            )
        }
    }
}

internal sealed interface ProfileEvent : BaseEvent {
    data class NavigateToUserRecipe(val userRecipeId: Long) : ProfileEvent
    data class NavigateToEditUserRecipe(val userRecipeId: Long) : ProfileEvent
}
