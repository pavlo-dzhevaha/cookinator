package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Observes the list of recipes by dish type from the local database.
 */
interface ObserveRecipesByDishTypeUseCase {
    operator fun invoke(dishType: String): Flow<List<Recipe>>
}
