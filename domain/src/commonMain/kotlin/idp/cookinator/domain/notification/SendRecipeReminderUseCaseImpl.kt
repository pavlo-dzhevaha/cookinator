package idp.cookinator.domain.notification

import idp.cookinator.domain.RecipeReminderSender
import idp.cookinator.domain.internal.useCaseIo

internal class SendRecipeReminderUseCaseImpl(
    private val reminderSender: RecipeReminderSender,
) : SendRecipeReminderUseCase {
    override suspend fun invoke(): Result<Unit> = useCaseIo {
        reminderSender.sendRandomReminder()
    }
}
