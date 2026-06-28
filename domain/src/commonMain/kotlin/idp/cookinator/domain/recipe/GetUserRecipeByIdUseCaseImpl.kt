package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.internal.useCaseIo

internal class GetUserRecipeByIdUseCaseImpl(
    private val database: DatabaseManager,
) : GetUserRecipeByIdUseCase {
    override suspend fun invoke(userRecipeId: Long) = useCaseIo {
        database.getUserRecipeById(userRecipeId)
    }
}
