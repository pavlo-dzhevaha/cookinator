package idp.cookinator.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.context.GlobalContext

class FavoriteRecipeReminderWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val sender = GlobalContext.get().get<FavoriteRecipeNotificationSender>()
        sender.sendRandomFavoriteReminder()
        return Result.success()
    }
}
