package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveLikedRecipeIdsUseCaseImpl(
    private val database: RecipeDatabaseManager,
) : ObserveLikedRecipeIdsUseCase {
    override fun invoke(): Flow<List<Int>> = database
        .observeLikedRecipeIds()
        .getOrDefault(flowOf(emptyList()))
}
