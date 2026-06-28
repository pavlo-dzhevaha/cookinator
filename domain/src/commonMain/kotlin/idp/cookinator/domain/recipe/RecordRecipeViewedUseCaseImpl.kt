package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog

internal class RecordRecipeViewedUseCaseImpl(
    private val database: DatabaseManager,
) : RecordRecipeViewedUseCase {
    override suspend fun invoke(recipeId: Int): Result<Unit> = useCaseIo {
        database.recordRecipeViewed(recipeId)
            .onSuccess {
                log { "Recorded view for recipe $recipeId" }
            }.onFailure { e ->
                log { "Failed to record view for recipe $recipeId. Error: ${e.message}" }
            }
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "RecordRecipeViewedUseCase"
    }
}
