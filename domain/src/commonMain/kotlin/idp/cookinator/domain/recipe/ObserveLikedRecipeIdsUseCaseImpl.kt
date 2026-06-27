package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveLikedRecipeIdsUseCaseImpl(
    private val database: DatabaseManager,
) : ObserveLikedRecipeIdsUseCase {
    override fun invoke(): Flow<List<Int>> = database
        .observeLikedRecipeIds()
        .getOrDefault(flowOf(emptyList()))
}
