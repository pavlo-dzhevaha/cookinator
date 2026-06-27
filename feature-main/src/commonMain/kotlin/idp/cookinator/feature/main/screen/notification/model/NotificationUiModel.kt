package idp.cookinator.feature.main.screen.notification.model

import idp.cookinator.model.AppNotification
import idp.cookinator.feature.main.screen.notification.util.groupNotificationsByDay

internal data class NotificationUiModel(
    val id: Long,
    val recipeId: Int,
    val title: String,
    val body: String,
    val imageUrl: String?,
    val createdAt: Long,
    val isRead: Boolean,
) {
    companion object {
        fun from(notification: AppNotification) = NotificationUiModel(
            id = notification.id,
            recipeId = notification.recipeId,
            title = notification.title,
            body = notification.body,
            imageUrl = notification.imageUrl,
            createdAt = notification.createdAt,
            isRead = notification.isRead,
        )

        val stubs: List<NotificationUiModel>
            get() {
                val now = System.currentTimeMillis()
                return listOf(
                    NotificationUiModel(
                        id = 1,
                        recipeId = 1,
                        title = "Time to Cook!",
                        body = "How about Pasta Carbonara?",
                        imageUrl = "https://img.spoonacular.com/recipes/633080-556x370.jpg",
                        createdAt = now - 3_600_000,
                        isRead = false,
                    ),
                    NotificationUiModel(
                        id = 2,
                        recipeId = 2,
                        title = "Time to Cook!",
                        body = "How about Greek Salad?",
                        imageUrl = null,
                        createdAt = now - 5_400_000,
                        isRead = true,
                    ),
                    NotificationUiModel(
                        id = 3,
                        recipeId = 3,
                        title = "Time to Cook!",
                        body = "How about Chicken Curry?",
                        imageUrl = null,
                        createdAt = now - 86_400_000,
                        isRead = false,
                    ),
                    NotificationUiModel(
                        id = 4,
                        recipeId = 4,
                        title = "Time to Cook!",
                        body = "How about Beef Stew?",
                        imageUrl = null,
                        createdAt = now - 172_800_000,
                        isRead = true,
                    ),
                )
            }

        val previewSections
            get() = groupNotificationsByDay(stubs)
    }
}
