package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog

internal class SetRecipeLikedUseCaseImpl(
    private val database: RecipeDatabaseManager,
) : SetRecipeLikedUseCase {
    override suspend fun invoke(recipeId: Int, isLiked: Boolean): Result<Unit> = useCaseIo {
        database.toggleRecipeLike(recipeId, isLiked)
            .onSuccess {
                log { "Successfully updated like state for recipe $recipeId to $isLiked" }
            }.onFailure { e ->
                log { "Failed to update like state for recipe $recipeId. Error: ${e.message}" }
            }
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "SetRecipeLikedUseCase"
    }
}
