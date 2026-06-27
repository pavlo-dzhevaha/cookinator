package idp.cookinator.feature.main.screen.notification.model

internal data class NotificationSection(
    val dayStartEpochMs: Long,
    val items: List<NotificationUiModel>,
)
