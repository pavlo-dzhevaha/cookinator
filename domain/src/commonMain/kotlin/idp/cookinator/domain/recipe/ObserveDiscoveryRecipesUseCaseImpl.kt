package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

internal class ObserveDiscoveryRecipesUseCaseImpl(
    private val database: RecipeDatabaseManager,
    private val orderStore: DiscoveryOrderStore,
) : ObserveDiscoveryRecipesUseCase {
    override fun invoke(): Flow<List<Recipe>> {
        val recipesFlow = database.observeAllRecipes().getOrElse {
            return flowOf(emptyList())
        }
        return recipesFlow.combine(orderStore.observeOrder()) { recipes, order ->
            when {
                recipes.isEmpty() -> emptyList()
                order.isEmpty() -> recipes
                else -> applyDiscoveryOrder(recipes, order)
            }
        }
    }
}
