package idp.cookinator.domain

interface RecipeReminderSender {
    suspend fun sendRandomReminder(): Result<Unit>
}
