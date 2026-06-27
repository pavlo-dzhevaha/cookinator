package idp.cookinator.feature.main.screen.notification.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent
import idp.cookinator.feature.main.screen.notification.model.NotificationFilter
import idp.cookinator.feature.main.screen.notification.model.NotificationUiModel

internal sealed interface NotificationsIntent : BaseIntent {
    data class OnNotificationClick(val item: NotificationUiModel) : NotificationsIntent

    data class OnFilterSelected(val filter: NotificationFilter) : NotificationsIntent

    data object OnOptionsClick : NotificationsIntent

    data object OnDismissOptions : NotificationsIntent

    data object OnClearAll : NotificationsIntent

    data object OnSendNow : NotificationsIntent

    data object OnRetry : NotificationsIntent
}
