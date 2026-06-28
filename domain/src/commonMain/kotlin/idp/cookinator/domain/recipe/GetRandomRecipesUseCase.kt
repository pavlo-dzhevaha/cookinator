package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe

/**
 * Fetches a list of recipes for discovery surfaces such as the home trending section.
 *
 * The implementation uses a cache-first strategy:
 * 1. Return shuffled recipes from the local Room cache when available.
 * 2. Otherwise fetch from Supabase, persist to Room, and return.
 * 3. As a final fallback, fetch from Spoonacular, persist to Room, and return.
 */
interface GetRandomRecipesUseCase {
    /**
     * Loads recipes using the cache-first strategy described above.
     *
     * @param forceRefresh When `true`, ignores the in-memory session order and fetches again,
     * producing a new shuffle. When `false`, returns the cached session order when available.
     * @return [Result.success] with a shuffled list of recipes, or [Result.failure] if all
     * sources fail.
     */
    suspend operator fun invoke(forceRefresh: Boolean = false): Result<List<Recipe>>
}
