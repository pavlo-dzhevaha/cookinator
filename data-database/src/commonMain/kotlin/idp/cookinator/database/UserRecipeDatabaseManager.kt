package idp.cookinator.database

import idp.cookinator.database.dao.UserRecipeDao
import idp.cookinator.database.model.toDomainModel
import idp.cookinator.database.model.toEntity
import idp.cookinator.model.UserRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRecipeDatabaseManager(
    private val userRecipeDao: UserRecipeDao,
) {
    suspend fun insertUserRecipe(recipe: UserRecipe): Result<Unit> = runCatching {
        userRecipeDao.insert(recipe.toEntity())
    }

    suspend fun updateUserRecipe(recipe: UserRecipe): Result<Unit> = runCatching {
        userRecipeDao.update(recipe.toEntity())
    }

    suspend fun getUserRecipeById(id: Long): Result<UserRecipe?> = runCatching {
        userRecipeDao.getById(id)?.toDomainModel()
    }

    fun observeUserRecipes(): Result<Flow<List<UserRecipe>>> = runCatching {
        userRecipeDao.observeAll().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    suspend fun deleteUserRecipe(id: Long): Result<Unit> = runCatching {
        userRecipeDao.deleteById(id)
    }
}
