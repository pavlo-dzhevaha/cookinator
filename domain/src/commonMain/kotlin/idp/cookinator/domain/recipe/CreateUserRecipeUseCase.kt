package idp.cookinator.domain.recipe

import idp.cookinator.model.Ingredient
import idp.cookinator.model.UserRecipe

/**
 * Persists a user-authored recipe in the local [user_recipes] table.
 *
 * Used by the create-recipe screen when the user taps Save.
 */
interface CreateUserRecipeUseCase {
    /**
     * @param title Recipe title.
     * @param image Local file path for the recipe photo.
     * @param readyInMinutes Cooking time in minutes.
     * @param servings Number of servings (13 represents "12+").
     * @param vegetarian Diet flag.
     * @param vegan Diet flag.
     * @param glutenFree Diet flag.
     * @param dairyFree Diet flag.
     * @param dishTypes Selected categories.
     * @param ingredients Recipe ingredients.
     * @return [Result.success] with the saved [UserRecipe], or [Result.failure] on error.
     */
    suspend operator fun invoke(
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
