package idp.cookinator.domain.notification

/**
 * Sends an immediate recipe reminder notification.
 *
 * Typically used from developer or debug actions in the notifications screen to trigger a
 * reminder without waiting for the scheduled worker.
 */
interface SendRecipeReminderUseCase {
    /**
     * Dispatches a random recipe reminder through the platform notification sender.
     *
     * @return [Result.success] when the reminder is sent, or [Result.failure] otherwise.
     */
    suspend operator fun invoke(): Result<Unit>
}
