package idp.cookinator.domain.recipe

import kotlinx.coroutines.flow.Flow

/**
 * Observes the set of recipe IDs that the user has marked as liked.
 *
 * The UI can collect this flow to update saved-state indicators (for example, the heart icon)
 * without reloading the full recipe list.
 */
interface ObserveLikedRecipeIdsUseCase {
    /**
     * @return A [Flow] that emits the current liked recipe IDs whenever the local database changes.
     */
    operator fun invoke(): Flow<List<Int>>
}
