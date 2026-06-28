package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import idp.cookinator.model.UserRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal class ObserveUserRecipesUseCaseImpl(
    private val database: DatabaseManager,
) : ObserveUserRecipesUseCase {
    override fun invoke(): Flow<List<UserRecipe>> =
        database.observeUserRecipes().getOrElse { emptyFlow() }
}
