package idp.cookinator.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val recipeId: Int,
    val title: String,
    val body: String,
    val createdAt: Long,
    val isRead: Boolean = false,
)
