package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Observes the current discovery recipe list for the app session.
 *
 * The order is stable until [GetRandomRecipesUseCase] is invoked with [forceRefresh].
 */
interface ObserveDiscoveryRecipesUseCase {
    operator fun invoke(): Flow<List<Recipe>>
}
