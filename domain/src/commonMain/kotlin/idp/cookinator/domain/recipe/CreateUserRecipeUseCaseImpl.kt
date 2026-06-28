package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog
import idp.cookinator.model.Ingredient
import idp.cookinator.model.UserRecipe
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
internal class CreateUserRecipeUseCaseImpl(
    private val database: DatabaseManager,
) : CreateUserRecipeUseCase {
    override suspend fun invoke(
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
        var id = Clock.System.now().toEpochMilliseconds()
        val recipe = UserRecipe(
            id = id,
            title = title.trim(),
            image = image,
            readyInMinutes = readyInMinutes,
            servings = servings,
            summary = null,
            vegetarian = vegetarian,
            vegan = vegan,
            glutenFree = glutenFree,
            dairyFree = dairyFree,
            dishTypes = dishTypes,
            extendedIngredients = ingredients,
            analyzedInstructions = emptyList(),
            createdAt = id,
        )

        var attempts = 0
        while (attempts < MAX_INSERT_ATTEMPTS) {
            val result = database.insertUserRecipe(recipe.copy(id = id, createdAt = id))
            if (result.isSuccess) {
                log { "Created user recipe $id" }
                return@useCaseIo Result.success(recipe.copy(id = id, createdAt = id))
            }
            id += 1
            attempts += 1
        }

        Result.failure(IllegalStateException("Failed to insert user recipe after $MAX_INSERT_ATTEMPTS attempts"))
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "CreateUserRecipeUseCase"
        const val MAX_INSERT_ATTEMPTS = 5
    }
}
