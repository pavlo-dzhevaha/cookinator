package idp.cookinator.notification

import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.local.localNotifier
import idp.cookinator.database.DatabaseManager

class FavoriteRecipeNotificationSender(
    private val database: DatabaseManager,
) {
    suspend fun sendRandomFavoriteReminder() {
        val recipe = database
            .getLikedRecipes()
            .getOrNull()
            ?.randomOrNull()
            ?: return

        KMPNotifier.localNotifier.notify(
            title = "Time to Cook!",
            body = "How about ${recipe.title}?",
            payloadData = mapOf(NotificationPayloadKeys.RECIPE_ID to recipe.id.toString()),
        )
    }
}
