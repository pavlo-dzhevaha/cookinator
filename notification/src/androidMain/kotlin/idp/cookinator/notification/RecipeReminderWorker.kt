package idp.cookinator.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mmk.kmpnotifier.notification.NotifierManager

class RecipeReminderWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val title = inputData.getString("RECIPE_TITLE") ?: "your recipe"

        // Use KMPNotifier to actually display the alert!
        val notifier = NotifierManager.getLocalNotifier()
        notifier.notify(
            title = "Time to Cook! 🍳",
            body = "It's time to start preparing $title!"
        )

        return Result.success()
    }
}
