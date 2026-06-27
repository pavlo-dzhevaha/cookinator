package idp.cookinator.domain.notification

import idp.cookinator.model.AppNotification
import kotlinx.coroutines.flow.Flow

/**
 * Observes in-app notification history stored locally.
 *
 * The UI can collect this flow to render the notifications list and react to new, read, or
 * cleared items.
 */
interface ObserveNotificationsUseCase {
    /**
     * @return A [Flow] that emits the current notification history whenever the local database
     * changes.
     */
    operator fun invoke(): Flow<List<AppNotification>>
}
