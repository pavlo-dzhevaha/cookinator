package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow

internal class ObserveDiscoveryRecipesUseCaseImpl(
    private val store: RecipeDiscoveryStore,
) : ObserveDiscoveryRecipesUseCase {
    override fun invoke(): Flow<List<Recipe>> = store.observe()
}
