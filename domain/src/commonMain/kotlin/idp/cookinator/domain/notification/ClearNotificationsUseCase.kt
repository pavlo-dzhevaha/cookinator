package idp.cookinator.domain.notification

/**
 * Removes all in-app notifications from local history.
 */
interface ClearNotificationsUseCase {
    /**
     * Clears the notification history in the local database.
     *
     * @return [Result.success] when all notifications are removed, or [Result.failure] otherwise.
     */
    suspend operator fun invoke(): Result<Unit>
}
