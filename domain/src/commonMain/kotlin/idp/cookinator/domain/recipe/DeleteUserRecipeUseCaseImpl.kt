package idp.cookinator.domain.recipe

import idp.cookinator.database.UserRecipeDatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog

internal class DeleteUserRecipeUseCaseImpl(
    private val database: UserRecipeDatabaseManager,
) : DeleteUserRecipeUseCase {
    override suspend fun invoke(userRecipeId: Long): Result<Unit> = useCaseIo {
        database.deleteUserRecipe(userRecipeId).fold(
            onSuccess = {
                log { "Deleted user recipe $userRecipeId" }
                Result.success(Unit)
            },
            onFailure = { Result.failure(it) },
        )
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "DeleteUserRecipeUseCase"
    }
}
