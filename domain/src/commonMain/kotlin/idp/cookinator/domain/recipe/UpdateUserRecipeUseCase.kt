package idp.cookinator.domain.recipe

import idp.cookinator.model.Ingredient
import idp.cookinator.model.UserRecipe

/**
 * Updates an existing user-authored recipe in the local [user_recipes] table.
 *
 * Used by the edit-recipe flow opened from user recipe detail.
 */
interface UpdateUserRecipeUseCase {
    /**
     * @param userRecipeId Existing recipe id.
     * @return [Result.success] with the updated [UserRecipe], or [Result.failure] on error.
     */
    suspend operator fun invoke(
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
    ): Result<UserRecipe>
}
