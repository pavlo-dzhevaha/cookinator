package idp.cookinator.database.dao

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import idp.cookinator.database.model.LikedRecipeEntity
import idp.cookinator.database.model.NotificationEntity
import idp.cookinator.database.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipe: List<RecipeEntity>)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun likeRecipe(likedRecipe: LikedRecipeEntity)

    @Query("DELETE FROM liked_recipes WHERE recipeId = :id")
    suspend fun unlikeRecipe(id: Int)

    @Query("SELECT recipeId FROM liked_recipes")
    fun observeLikedRecipeIds(): Flow<List<Int>>

    @Query(
        """
        SELECT recipes.* FROM recipes
        INNER JOIN liked_recipes ON recipes.id = liked_recipes.recipeId
        """,
    )
    suspend fun getLikedRecipes(): List<RecipeEntity>

    @Query(
        """
        SELECT recipes.* FROM recipes
        INNER JOIN liked_recipes ON recipes.id = liked_recipes.recipeId
        """,
    )
    fun observeLikedRecipes(): Flow<List<RecipeEntity>>
}

@Database(
    entities = [
        RecipeEntity::class,
        LikedRecipeEntity::class,
        NotificationEntity::class,
    ],
    version = 2,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    abstract fun notificationDao(): NotificationDao
}

// Room 2.7+ uses this to automatically generate the implementation behind the scenes
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
