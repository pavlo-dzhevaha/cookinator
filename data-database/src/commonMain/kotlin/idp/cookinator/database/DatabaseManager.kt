package idp.cookinator.database

import idp.cookinator.database.dao.RecipeDao
import idp.cookinator.database.model.LikedRecipeEntity
import idp.cookinator.database.model.RecipeEntity
import idp.cookinator.database.model.toDomainModel
import idp.cookinator.database.model.toEntity
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Manages database operations related to recipes. This class provides methods to save recipes to the
 * local database and to fetch cached recipes. It uses a [RecipeDao] to perform the actual database
 * operations.
 *
 * @property recipeDao The Data Access Object (DAO) for performing database operations on recipes.
 */
class DatabaseManager(
    private val recipeDao: RecipeDao,
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
     * Returns a reactive Flow of liked recipe IDs as Strings.
     */
    fun observeLikedRecipeIds(): Result<Flow<List<Int>>> =
        runCatching { recipeDao.observeLikedRecipeIds() }

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
}
