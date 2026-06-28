package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveRecipesByDishTypeUseCaseImpl(
    private val database: RecipeDatabaseManager,
) : ObserveRecipesByDishTypeUseCase {
    override fun invoke(dishType: String): Flow<List<Recipe>> = database
        .observeRecipesByDishType(dishType)
        .getOrDefault(flowOf(emptyList()))
}
