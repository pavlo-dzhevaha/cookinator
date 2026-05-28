package idp.cookinator.feature.main.screen.saved.contract

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseState
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel

internal data class SavedState(
    val uiState: UiState,
    val items: List<RecipeUiModel>,
) : BaseState {
    companion object {
        val initialState = SavedState(
            uiState = UiState.LOADING,
            items = emptyList(),
        )
    }
}
