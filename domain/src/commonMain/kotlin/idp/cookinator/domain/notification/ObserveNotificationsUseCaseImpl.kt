package idp.cookinator.domain.notification

import idp.cookinator.database.NotificationDatabaseManager
import idp.cookinator.model.AppNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveNotificationsUseCaseImpl(
    private val database: NotificationDatabaseManager,
) : ObserveNotificationsUseCase {
    override fun invoke(): Flow<List<AppNotification>> = database
        .observeNotifications()
        .getOrDefault(flowOf(emptyList()))
}
