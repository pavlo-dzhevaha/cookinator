package idp.cookinator.database

import idp.cookinator.database.dao.NotificationDao
import idp.cookinator.database.dao.RecipeDao
import idp.cookinator.database.model.LikedRecipeEntity
import idp.cookinator.database.model.NotificationEntity
import idp.cookinator.database.model.RecipeEntity
import idp.cookinator.database.model.toDomainModel
import idp.cookinator.database.model.toEntity
import idp.cookinator.model.AppNotification
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Manages database operations related to recipes. This class provides methods to save recipes to the
 * local database and to fetch cached recipes. It uses a [RecipeDao] to perform the actual database
 * operations.
 *
 * @property recipeDao The Data Access Object (DAO) for performing database operations on recipes.
 */
class DatabaseManager(
    private val recipeDao: RecipeDao,
    private val notificationDao: NotificationDao,
) {
    /**
     * Saves a list of [Recipe] objects to the local database. Each [Recipe] is converted to a
     * [RecipeEntity] before being inserted.
     *
     * @param recipes The list of [Recipe] objects to be saved.
     */
    suspend fun saveRecipes(recipes: List<Recipe>) = runCatching {
        recipeDao
            .insertRecipes(recipes.map(Recipe::toEntity))
    }

    /**
     * Fetches all cached recipes from the local database.
     *
     * @return A [Result] containing a list of [Recipe] on success, or an [Exception] on failure.
     */
    suspend fun getAllCachedRecipes(): Result<List<Recipe>> = runCatching {
        recipeDao
            .getAllRecipes()
            .map(RecipeEntity::toDomainModel)
    }

    /**
     * Fetches all liked recipes from the local database.
     */
    suspend fun getLikedRecipes(): Result<List<Recipe>> = runCatching {
        recipeDao
            .getLikedRecipes()
            .map(RecipeEntity::toDomainModel)
    }

    /**
     * Returns a reactive Flow of liked recipe IDs.
     */
    fun observeLikedRecipeIds(): Result<Flow<List<Int>>> =
        runCatching { recipeDao.observeLikedRecipeIds() }

    /**
     * Returns a reactive Flow of liked recipes from the local database.
     */
    fun observeLikedRecipes(): Result<Flow<List<Recipe>>> = runCatching {
        recipeDao
            .observeLikedRecipes()
            .map { entities -> entities.map(RecipeEntity::toDomainModel) }
    }

    /**
     * Toggles the liked state of a recipe in the local database.
     */
    suspend fun toggleRecipeLike(recipeId: Int, isLiked: Boolean) = runCatching {
        if (isLiked) {
            recipeDao.likeRecipe(LikedRecipeEntity(recipeId))
        } else {
            recipeDao.unlikeRecipe(recipeId)
        }
    }

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
