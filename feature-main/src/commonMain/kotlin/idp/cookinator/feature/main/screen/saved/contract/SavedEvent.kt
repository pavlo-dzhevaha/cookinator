package idp.cookinator.feature.main.screen.saved.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface SavedEvent : BaseEvent {
    data class NavigateToRecipe(val recipeId: Int) : SavedEvent
}