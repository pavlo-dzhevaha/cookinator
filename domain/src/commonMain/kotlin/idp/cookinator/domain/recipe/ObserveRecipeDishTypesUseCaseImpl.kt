package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class ObserveRecipeDishTypesUseCaseImpl(
    private val database: DatabaseManager,
) : ObserveRecipeDishTypesUseCase {
    override fun invoke(): Flow<List<String>> = database
        .observeDishTypes()
        .getOrDefault(flowOf(emptyList()))
}
