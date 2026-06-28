package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveRecipeDishTypesUseCaseImpl(
    private val database: RecipeDatabaseManager,
) : ObserveRecipeDishTypesUseCase {
    override fun invoke(): Flow<List<String>> = database
        .observeDishTypes()
        .getOrDefault(flowOf(emptyList()))
}
