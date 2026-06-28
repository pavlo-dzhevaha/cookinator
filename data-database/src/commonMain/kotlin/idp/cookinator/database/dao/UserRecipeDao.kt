package idp.cookinator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import idp.cookinator.database.model.UserRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserRecipeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(recipe: UserRecipeEntity)

    @Update
    suspend fun update(recipe: UserRecipeEntity)

    @Query("SELECT * FROM user_recipes WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): UserRecipeEntity?

    @Query("SELECT * FROM user_recipes ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<UserRecipeEntity>>

    @Query("DELETE FROM user_recipes WHERE id = :id")
    suspend fun deleteById(id: Long)
}
