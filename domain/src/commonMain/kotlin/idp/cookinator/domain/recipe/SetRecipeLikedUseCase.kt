package idp.cookinator.domain.recipe

/**
 * Updates whether a recipe is liked by the user.
 *
 * Persisting the change locally triggers reactive observers such as
 * [ObserveLikedRecipeIdsUseCase] and [ObserveLikedRecipesUseCase].
 */
interface SetRecipeLikedUseCase {
    /**
     * Sets the liked state for [recipeId].
     *
     * @param recipeId Identifier of the recipe to update.
     * @param isLiked `true` to save the recipe, `false` to remove it from saved recipes.
     * @return [Result.success] when the database update succeeds, or [Result.failure] otherwise.
     */
    suspend operator fun invoke(recipeId: Int, isLiked: Boolean): Result<Unit>
}
