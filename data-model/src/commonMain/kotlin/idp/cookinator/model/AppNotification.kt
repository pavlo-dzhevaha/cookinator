package idp.cookinator.model

data class AppNotification(
    val id: Long,
    val recipeId: Int,
    val title: String,
    val body: String,
    val imageUrl: String?,
    val createdAt: Long,
    val isRead: Boolean,
)
