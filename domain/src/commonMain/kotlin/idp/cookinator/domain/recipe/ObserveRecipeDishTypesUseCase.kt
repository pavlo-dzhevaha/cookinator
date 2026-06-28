package idp.cookinator.domain.recipe

import kotlinx.coroutines.flow.Flow

/**
 * Observes the list of recipe dish types from the local database.
 */
interface ObserveRecipeDishTypesUseCase {
    /**
     * @return A [Flow] that emits the current list of recipe dish types whenever the local database changes.
     */
    operator fun invoke(): Flow<List<String>>
}
