package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Observes discovery recipes from Room using the persisted shuffle order.
 *
 * The order is stable until [SyncDiscoveryRecipesUseCase] is invoked with [forceRefresh].
 */
interface ObserveDiscoveryRecipesUseCase {
    operator fun invoke(): Flow<List<Recipe>>
}
