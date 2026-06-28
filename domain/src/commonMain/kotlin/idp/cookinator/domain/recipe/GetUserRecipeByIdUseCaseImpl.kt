package idp.cookinator.domain.recipe

import idp.cookinator.database.UserRecipeDatabaseManager
import idp.cookinator.domain.internal.useCaseIo

internal class GetUserRecipeByIdUseCaseImpl(
    private val database: UserRecipeDatabaseManager,
) : GetUserRecipeByIdUseCase {
    override suspend fun invoke(userRecipeId: Long) = useCaseIo {
        database.getUserRecipeById(userRecipeId)
    }
}
