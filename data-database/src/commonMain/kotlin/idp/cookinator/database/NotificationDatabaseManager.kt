package idp.cookinator.database

import idp.cookinator.database.dao.NotificationDao
import idp.cookinator.database.model.NotificationEntity
import idp.cookinator.database.model.toDomainModel
import idp.cookinator.model.AppNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationDatabaseManager(
    private val notificationDao: NotificationDao,
) {
    fun observeNotifications(): Result<Flow<List<AppNotification>>> = runCatching {
        notificationDao
            .observeAll()
            .map { entities -> entities.map { it.toDomainModel() } }
    }

    suspend fun insertNotification(
        recipeId: Int,
        title: String,
        body: String,
        createdAt: Long = System.currentTimeMillis(),
    ): Result<Long> = runCatching {
        notificationDao.insert(
            NotificationEntity(
                recipeId = recipeId,
                title = title,
                body = body,
                createdAt = createdAt,
            ),
        )
    }

    suspend fun markNotificationRead(id: Long): Result<Unit> = runCatching {
        notificationDao.markRead(id)
    }

    suspend fun clearNotifications(): Result<Unit> = runCatching {
        notificationDao.deleteAll()
    }
}
