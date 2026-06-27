package idp.cookinator.feature.main.screen.notification.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface NotificationsEvent : BaseEvent {
    data class NavigateToRecipe(val recipeId: Int) : NotificationsEvent
}
