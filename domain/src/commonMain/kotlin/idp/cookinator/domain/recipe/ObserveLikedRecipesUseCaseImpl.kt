package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveLikedRecipesUseCaseImpl(
    private val database: RecipeDatabaseManager,
) : ObserveLikedRecipesUseCase {
    override fun invoke(): Flow<List<Recipe>> = database
        .observeLikedRecipes()
        .getOrDefault(flowOf(emptyList()))
}
