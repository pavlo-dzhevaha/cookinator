package idp.cookinator.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import idp.cookinator.model.AppNotification

data class NotificationWithImageEntity(
    @Embedded val notification: NotificationEntity,
    @ColumnInfo(name = "image") val imageUrl: String?,
)

fun NotificationWithImageEntity.toDomainModel(): AppNotification = AppNotification(
    id = notification.id,
    recipeId = notification.recipeId,
    title = notification.title,
    body = notification.body,
    imageUrl = imageUrl,
    createdAt = notification.createdAt,
    isRead = notification.isRead,
)
