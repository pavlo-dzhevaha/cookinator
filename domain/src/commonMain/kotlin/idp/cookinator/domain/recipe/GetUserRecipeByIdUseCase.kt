package idp.cookinator.domain.recipe

import idp.cookinator.model.UserRecipe

/**
 * Loads a single user-authored recipe by id for detail and edit screens.
 */
interface GetUserRecipeByIdUseCase {
    suspend operator fun invoke(userRecipeId: Long): Result<UserRecipe?>
}
