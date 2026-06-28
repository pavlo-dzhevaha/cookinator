package idp.cookinator.domain.recipe

/**
 * Records that the user opened a recipe detail screen.
 *
 * Re-viewing the same recipe updates its timestamp so it moves to the front of
 * [ObserveRecentlyViewedUseCase] results without creating duplicates.
 */
interface RecordRecipeViewedUseCase {
    /**
     * Persists a view event for [recipeId] in the local database.
     *
     * @param recipeId Identifier of the recipe that was successfully loaded.
     * @return [Result.success] when the record is stored, or [Result.failure] otherwise.
     */
    suspend operator fun invoke(recipeId: Int): Result<Unit>
}
