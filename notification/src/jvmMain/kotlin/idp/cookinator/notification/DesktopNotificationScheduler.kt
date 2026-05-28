package idp.cookinator.notification

import com.mmk.kmpnotifier.notification.NotifierManager
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DesktopNotificationScheduler : NotificationScheduler {

    // A background thread to keep track of time
    private val scheduler = Executors.newSingleThreadScheduledExecutor()

    override fun scheduleCookingReminder(recipeTitle: String, delayHours: Long) {
        scheduler.schedule({
            // Trigger KMPNotifier when the delay is over
            val notifier = NotifierManager.getLocalNotifier()
            notifier.notify(
                title = "Time to Cook! 🍳",
                body = "It's time to start preparing $recipeTitle!"
            )
        }, delayHours, TimeUnit.HOURS)
    }
}