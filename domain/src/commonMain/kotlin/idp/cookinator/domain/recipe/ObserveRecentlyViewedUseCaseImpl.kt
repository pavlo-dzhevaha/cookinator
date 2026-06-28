package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveRecentlyViewedUseCaseImpl(
    private val database: DatabaseManager,
) : ObserveRecentlyViewedUseCase {
    override fun invoke(limit: Int?): Flow<List<Recipe>> = database
        .observeRecentlyViewedRecipes(limit)
        .getOrDefault(flowOf(emptyList()))
}
