package idp.cookinator.feature.recipe.screen.detail.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface RecipeDetailEvent : BaseEvent {
    data class NavigateToEdit(val userRecipeId: Long) : RecipeDetailEvent
}
