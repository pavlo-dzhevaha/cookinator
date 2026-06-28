package idp.cookinator.domain.recipe

import idp.cookinator.database.UserRecipeDatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog
import idp.cookinator.model.Ingredient
import idp.cookinator.model.UserRecipe

internal class UpdateUserRecipeUseCaseImpl(
    private val database: UserRecipeDatabaseManager,
) : UpdateUserRecipeUseCase {
    override suspend fun invoke(
        userRecipeId: Long,
        title: String,
        image: String,
        readyInMinutes: Int,
        servings: Int,
        vegetarian: Boolean,
        vegan: Boolean,
        glutenFree: Boolean,
        dairyFree: Boolean,
        dishTypes: List<String>,
        ingredients: List<Ingredient>,
    ): Result<UserRecipe> = useCaseIo {
        database
            .getUserRecipeById(userRecipeId)
            .fold(
                onSuccess = { existing ->
                    if (existing == null) {
                        return@useCaseIo Result.failure(NoSuchElementException("User recipe $userRecipeId not found"))
                    }
                    val updated = existing.copy(
                        title = title.trim(),
                        image = image,
                        readyInMinutes = readyInMinutes,
                        servings = servings,
                        vegetarian = vegetarian,
                        vegan = vegan,
                        glutenFree = glutenFree,
                        dairyFree = dairyFree,
                        dishTypes = dishTypes,
                        extendedIngredients = ingredients,
                    )
                    database
                        .updateUserRecipe(updated)
                        .fold(
                            onSuccess = {
                                log { "Updated user recipe $userRecipeId" }
                                Result.success(updated)
                            },
                            onFailure = { Result.failure(it) },
                        )
                },
                onFailure = { Result.failure(it) },
            )
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "UpdateUserRecipeUseCase"
    }
}
