package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog
import idp.cookinator.model.Recipe
import idp.cookinator.network.NetworkManager
import idp.cookinator.network.model.toDomainModel

internal class GetRecipeByIdUseCaseImpl(
    private val network: NetworkManager,
    private val database: RecipeDatabaseManager,
) : GetRecipeByIdUseCase {
    override suspend fun invoke(recipeId: Int): Result<Recipe> = useCaseIo {
        database
            .getCachedRecipeById(recipeId)
            .onSuccess { cached ->
                if (cached != null) {
                    log { "Returning recipe $recipeId from the database cache." }
                    return@useCaseIo Result.success(cached)
                }
            }.onFailure { e ->
                log { "Failed to fetch recipe $recipeId from the database. Error: ${e.message}" }
            }

        network
            .getRecipeInformation(recipeId)
            .map { it.toDomainModel() }
            .onSuccess { recipe ->
                log { "Fetched recipe $recipeId from the network." }
                database
                    .saveRecipes(listOf(recipe))
                    .onSuccess {
                        log { "Successfully saved recipe $recipeId to the database." }
                    }.onFailure { e ->
                        log { "Failed to save recipe $recipeId to the database. Error: ${e.message}" }
                    }
            }.onFailure { e ->
                log { "Failed to fetch recipe $recipeId from the network. Error: ${e.message}" }
            }
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "GetRecipeByIdUseCase"
    }
}
