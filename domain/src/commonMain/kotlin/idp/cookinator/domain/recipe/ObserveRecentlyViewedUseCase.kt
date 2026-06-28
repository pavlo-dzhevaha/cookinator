package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Observes recipes the user has recently opened, ordered from newest to oldest view.
 *
 * Used by the home recently-viewed section and the full list opened via "See all".
 */
interface ObserveRecentlyViewedUseCase {
    /**
     * @param limit When set, caps the number of emitted recipes (e.g. home preview). When `null`,
     *   emits the full stored history.
     * @return A [Flow] that emits recently viewed [Recipe] items whenever the local database changes.
     */
    operator fun invoke(limit: Int? = null): Flow<List<Recipe>>
}
