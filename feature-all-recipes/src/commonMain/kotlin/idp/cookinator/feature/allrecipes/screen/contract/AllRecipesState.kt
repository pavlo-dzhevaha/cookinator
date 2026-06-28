package idp.cookinator.feature.allrecipes.screen.contract

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseState

internal data class AllRecipesState(
    val uiState: UiState,
    val items: List<RecipeUiModel>,
) : BaseState {
    companion object {
        val initialState = AllRecipesState(
            uiState = UiState.LOADING,
            items = emptyList(),
        )
    }
}
