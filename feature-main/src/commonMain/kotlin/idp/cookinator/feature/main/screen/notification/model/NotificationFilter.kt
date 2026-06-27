package idp.cookinator.feature.main.screen.notification.model

import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.notifications_empty_read
import cookinator.localisation.generated.resources.notifications_empty_unread
import org.jetbrains.compose.resources.StringResource

internal enum class NotificationFilter {
    All,
    Unread,
    Read,
}

internal fun NotificationFilter.emptyMessage(): StringResource = when (this) {
    NotificationFilter.Unread -> Res.string.notifications_empty_unread
    NotificationFilter.Read -> Res.string.notifications_empty_read
    NotificationFilter.All -> Res.string.notifications_empty_unread
}
