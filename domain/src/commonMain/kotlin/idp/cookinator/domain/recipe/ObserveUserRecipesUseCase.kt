package idp.cookinator.domain.recipe

import idp.cookinator.model.UserRecipe
import kotlinx.coroutines.flow.Flow

/**
 * Observes all user-authored recipes for the profile screen.
 */
interface ObserveUserRecipesUseCase {
    operator fun invoke(): Flow<List<UserRecipe>>
}
