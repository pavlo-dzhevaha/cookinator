package idp.cookinator.domain.recipe

import idp.cookinator.database.DatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog
import idp.cookinator.model.Recipe
import idp.cookinator.network.NetworkManager
import idp.cookinator.network.model.RandomRecipesResponse
import idp.cookinator.network.model.toDomainModels

internal class GetRandomRecipesUseCaseImpl(
    private val network: NetworkManager,
    private val database: DatabaseManager,
) : GetRandomRecipesUseCase {
    override suspend fun invoke(): Result<List<Recipe>> = useCaseIo {
        database
            .getAllCachedRecipes()
            .onSuccess { list ->
                log { "Fetched ${list.size} cached recipes from the database." }
                if (list.isEmpty()) return@onSuccess
                log { "Returning ${list.size} cached recipes from the database." }
                return@useCaseIo Result.success(list.shuffled())
            }.onFailure { e ->
                log { "Failed to fetch cached recipes from the database. Error: ${e.message}" }
            }

        network
            .getServerRecipes()
            .map(RandomRecipesResponse::toDomainModels)
            .onSuccess { models ->
                log { "Fetched ${models.size} recipes from the server." }
                if (models.isEmpty()) return@onSuccess
                database
                    .saveRecipes(models)
                    .onSuccess {
                        log { "Successfully saved ${models.size} recipes fetched from the server to the database." }
                    }.onFailure {
                        log { "Failed to save recipes fetched from the server to the database. Error: ${it.message}" }
                    }
                log { "Returning ${models.size} recipes fetched from the server and saved to the database." }
                return@useCaseIo Result.success(models.shuffled())
            }.onFailure {
                log { "Failed to fetch recipes from the server. Error: ${it.message}" }
            }

        network
            .getRandomRecipes()
            .map(RandomRecipesResponse::toDomainModels)
            .onSuccess { models ->
                log { "Returning ${models.size} recipes fetched from the network and saved to the database." }
                database.saveRecipes(models)
            }.onFailure {
                log { "Failed to fetch random recipes from the network. Error: ${it.message}" }
            }.map { it.shuffled() }
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "GetRandomRecipesUseCase"
    }
}
