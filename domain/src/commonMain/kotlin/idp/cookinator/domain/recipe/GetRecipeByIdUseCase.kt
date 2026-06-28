package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe

/**
 * Loads a single recipe by id using a cache-first strategy:
 * 1. Return from the local Room cache when available.
 * 2. Otherwise fetch from Spoonacular, persist to Room, and return.
 */
interface GetRecipeByIdUseCase {
    suspend operator fun invoke(recipeId: Int): Result<Recipe>
}
