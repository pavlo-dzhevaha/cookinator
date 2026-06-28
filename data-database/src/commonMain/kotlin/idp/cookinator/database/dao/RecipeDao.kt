package idp.cookinator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import idp.cookinator.database.model.LikedRecipeEntity
import idp.cookinator.database.model.RecipeDishTypeEntity
import idp.cookinator.database.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipe: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeDishTypes(types: List<RecipeDishTypeEntity>)

    @Query("DELETE FROM recipe_dish_types WHERE recipeId IN (:recipeIds)")
    suspend fun deleteDishTypesForRecipes(recipeIds: List<Int>)

    @Transaction
    suspend fun upsertRecipesWithDishTypes(
        recipes: List<RecipeEntity>,
        dishTypes: List<RecipeDishTypeEntity>,
    ) {
        insertRecipes(recipes)
        if (recipes.isNotEmpty()) {
            deleteDishTypesForRecipes(recipes.map { it.id })
        }
        if (dishTypes.isNotEmpty()) {
            insertRecipeDishTypes(dishTypes)
        }
    }

    @Query("SELECT DISTINCT dishType FROM recipe_dish_types ORDER BY dishType COLLATE NOCASE")
    fun observeDishTypes(): Flow<List<String>>

    @Query(
        """
        SELECT recipes.* FROM recipes
        INNER JOIN recipe_dish_types ON recipes.id = recipe_dish_types.recipeId
        WHERE recipe_dish_types.dishType = :dishType
        ORDER BY recipes.title COLLATE NOCASE
        """,
    )
    fun observeRecipesByDishType(dishType: String): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id = :id LIMIT 1")
    suspend fun getRecipeById(id: Int): RecipeEntity?

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
