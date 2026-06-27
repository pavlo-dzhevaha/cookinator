package idp.cookinator.domain.notification

import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.internal.useCaseIo

internal class ClearNotificationsUseCaseImpl(
    private val database: DatabaseManager,
) : ClearNotificationsUseCase {
    override suspend fun invoke(): Result<Unit> = useCaseIo {
        database.clearNotifications()
    }
}
