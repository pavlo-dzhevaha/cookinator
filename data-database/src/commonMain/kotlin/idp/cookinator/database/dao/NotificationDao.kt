package idp.cookinator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import idp.cookinator.database.model.NotificationEntity
import idp.cookinator.database.model.NotificationWithImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Insert
    suspend fun insert(notification: NotificationEntity): Long

    @Query(
        """
        SELECT notifications.*, recipes.image AS image FROM notifications
        LEFT JOIN recipes ON notifications.recipeId = recipes.id
        ORDER BY notifications.createdAt DESC
        """,
    )
    fun observeAll(): Flow<List<NotificationWithImageEntity>>

    @Query("UPDATE notifications SET isRead = 1 WHERE id = :id")
    suspend fun markRead(id: Long)

    @Query("DELETE FROM notifications")
    suspend fun deleteAll()
}
