package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseState
import idp.cookinator.model.Recipe

internal data class HomeState(
    val uiState: UiState,
    val query: String,
    val items: List<Recipe>,
) : BaseState {
    companion object {
        val initialState = HomeState(
            uiState = UiState.LOADING,
            query = "",
            items = emptyList(),
        )
    }
}
