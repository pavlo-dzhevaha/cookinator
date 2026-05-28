package idp.cookinator.notification

interface NotificationScheduler {
    /**
     * @param recipeTitle The title of the saved recipe.
     * @param delayHours How many hours from now the notification should trigger.
     */
    fun scheduleCookingReminder(recipeTitle: String, delayHours: Long)
}
