package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Observes the full list of recipes the user has saved.
 *
 * The UI can collect this flow to render the Saved tab and keep it in sync with like/unlike
 * actions from other screens.
 */
interface ObserveLikedRecipesUseCase {
    /**
     * @return A [Flow] that emits liked [Recipe] items whenever the local database changes.
     */
    operator fun invoke(): Flow<List<Recipe>>
}
