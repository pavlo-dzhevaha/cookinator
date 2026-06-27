package idp.cookinator.domain.notification

import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.internal.useCaseIo

internal class MarkNotificationReadUseCaseImpl(
    private val database: DatabaseManager,
) : MarkNotificationReadUseCase {
    override suspend fun invoke(id: Long): Result<Unit> = useCaseIo {
        database.markNotificationRead(id)
    }
}
