package idp.cookinator.notification

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking

class DesktopNotificationScheduler(
    private val sender: FavoriteRecipeNotificationSender,
) : NotificationScheduler {

    private val scheduler = Executors.newSingleThreadScheduledExecutor()

    override fun startPeriodicReminders() {
        scheduler.scheduleAtFixedRate(
            {
                runBlocking {
                    sender.sendRandomFavoriteReminder()
                }
            },
            REMINDER_INTERVAL_HOURS,
            REMINDER_INTERVAL_HOURS,
            TimeUnit.HOURS,
        )
    }

    private companion object {
        const val REMINDER_INTERVAL_HOURS = 2L
    }
}
