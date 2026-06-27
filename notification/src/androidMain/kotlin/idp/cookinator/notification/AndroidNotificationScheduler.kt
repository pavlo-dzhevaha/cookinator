package idp.cookinator.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class AndroidNotificationScheduler(
    private val context: Context,
) : NotificationScheduler {

    override fun startPeriodicReminders() {
        val workRequest = PeriodicWorkRequestBuilder<FavoriteRecipeReminderWorker>(
            repeatInterval = REMINDER_INTERVAL_HOURS,
            repeatIntervalTimeUnit = TimeUnit.HOURS,
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest,
        )
    }

    private companion object {
        const val WORK_NAME = "favorite_recipe_reminder"
        const val REMINDER_INTERVAL_HOURS = 2L
    }
}
