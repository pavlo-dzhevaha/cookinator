package idp.cookinator.notification

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class AndroidNotificationScheduler(
    private val context: Context,
) : NotificationScheduler {

    override fun scheduleCookingReminder(recipeTitle: String, delayHours: Long) {
        val inputData = workDataOf("RECIPE_TITLE" to recipeTitle)

        val workRequest = OneTimeWorkRequestBuilder<RecipeReminderWorker>()
            .setInitialDelay(delayHours, TimeUnit.HOURS) // The X hours delay
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
