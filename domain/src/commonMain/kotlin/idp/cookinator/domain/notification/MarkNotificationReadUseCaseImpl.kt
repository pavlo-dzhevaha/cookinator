package idp.cookinator.domain.notification

import idp.cookinator.database.NotificationDatabaseManager
import idp.cookinator.domain.internal.useCaseIo

internal class MarkNotificationReadUseCaseImpl(
    private val database: NotificationDatabaseManager,
) : MarkNotificationReadUseCase {
    override suspend fun invoke(id: Long): Result<Unit> = useCaseIo {
        database.markNotificationRead(id)
    }
}
