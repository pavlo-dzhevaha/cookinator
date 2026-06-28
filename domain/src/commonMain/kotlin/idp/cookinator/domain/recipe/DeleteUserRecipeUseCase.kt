package idp.cookinator.domain.recipe

/**
 * Deletes a user-created recipe from local storage.
 */
interface DeleteUserRecipeUseCase {
    suspend operator fun invoke(userRecipeId: Long): Result<Unit>
}
