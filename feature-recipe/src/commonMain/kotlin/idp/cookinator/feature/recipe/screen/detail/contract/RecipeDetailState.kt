package idp.cookinator.feature.recipe.screen.detail.contract

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseState
import idp.cookinator.model.Recipe

internal data class RecipeDetailState(
    val uiState: UiState,
    val recipe: Recipe?,
    val isSaved: Boolean,
    val isUserRecipe: Boolean = false,
    val userRecipeId: Long? = null,
) : BaseState {
    companion object {
        val initialState = RecipeDetailState(
            uiState = UiState.LOADING,
            recipe = null,
            isSaved = false,
        )
    }
}
