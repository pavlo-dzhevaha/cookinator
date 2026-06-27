package idp.cookinator.domain.notification

/**
 * Marks a single in-app notification as read.
 *
 * Used when the user opens a notification from the in-app list or taps a push notification.
 */
interface MarkNotificationReadUseCase {
    /**
     * Persists the read state for the notification with [id].
     *
     * @param id Local notification identifier.
     * @return [Result.success] when the update succeeds, or [Result.failure] otherwise.
     */
    suspend operator fun invoke(id: Long): Result<Unit>
}
