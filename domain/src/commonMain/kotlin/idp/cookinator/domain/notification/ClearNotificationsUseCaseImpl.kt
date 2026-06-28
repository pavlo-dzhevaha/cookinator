package idp.cookinator.domain.notification

import idp.cookinator.database.NotificationDatabaseManager
import idp.cookinator.domain.internal.useCaseIo

internal class ClearNotificationsUseCaseImpl(
    private val database: NotificationDatabaseManager,
) : ClearNotificationsUseCase {
    override suspend fun invoke(): Result<Unit> = useCaseIo {
        database.clearNotifications()
    }
}
