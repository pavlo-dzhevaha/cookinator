package idp.cookinator.notification

import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.local.localNotifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.notification_reminder_body
import cookinator.localisation.generated.resources.notification_reminder_title
import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.RecipeReminderSender
import idp.cookinator.model.Recipe
import org.jetbrains.compose.resources.getString

class FavoriteRecipeNotificationSender(
    private val database: DatabaseManager,
) : RecipeReminderSender {

    override suspend fun sendRandomReminder(): Result<Unit> = runCatching {
        val recipe = pickRandomRecipe() ?: return@runCatching
        sendReminderForRecipe(recipe)
    }

    suspend fun sendRandomFavoriteReminder() {
        sendRandomReminder()
    }

    private suspend fun sendReminderForRecipe(recipe: Recipe) {
        val title = getString(Res.string.notification_reminder_title)
        val body = getString(Res.string.notification_reminder_body, recipe.title)
        val notificationId = database
            .insertNotification(
                recipeId = recipe.id,
                title = title,
                body = body,
            )
            .getOrThrow()

        KMPNotifier.localNotifier.notify(
            id = notificationId.coerceAtMost(Int.MAX_VALUE.toLong()).toInt(),
            title = title,
            body = body,
            payloadData = mapOf(
                NotificationPayloadKeys.RECIPE_ID to recipe.id.toString(),
                NotificationPayloadKeys.NOTIFICATION_ID to notificationId.toString(),
            ),
        )
    }

    private suspend fun pickRandomRecipe(): Recipe? {
        database
            .getLikedRecipes()
            .getOrNull()
            ?.randomOrNull()
            ?.let { return it }

        return database
            .getAllCachedRecipes()
            .getOrNull()
            ?.randomOrNull()
    }
}
