package idp.cookinator.domain

import idp.cookinator.database.DatabaseManager
import idp.cookinator.model.Recipe
import idp.cookinator.network.NetworkManager
import idp.cookinator.network.model.RandomRecipesResponse
import idp.cookinator.network.model.toDomainModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

/**
 * The [DomainManager] class serves as the central point for managing the domain logic of the
 * application. It is responsible for fetching random recipes, either from the local database or
 * from the network. The class uses a [NetworkManager] to perform network operations and a
 * [DatabaseManager] to handle database interactions. The main method, [getRandomRecipes],
 * implements a multi-step process to retrieve recipes, prioritizing cached data and falling back
 * to network requests as needed.
 *
 * @property network The [NetworkManager] used for fetching recipes from the server and network.
 * @property database The [DatabaseManager] used for fetching cached recipes and saving new recipes to the local database.
 */
class DomainManager(
    private val network: NetworkManager,
    private val database: DatabaseManager,
) {
    /**
     * Fetches random recipes. The method first attempts to fetch cached recipes from the local
     * database. If cached recipes are found, they are returned immediately. If no cached recipes
     * are available, it fetches recipes from the server. If the server returns recipes, they are
     * saved to the local database and returned. If the server does not return any recipes, it
     * makes a final attempt to fetch random recipes from the network. If this final attempt is
     * successful, the recipes are saved to the database and returned. If all attempts fail, an
     * appropriate error is returned.
     */
    suspend fun getRandomRecipes(): Result<List<Recipe>> = hardWork {
        database
            .getAllCachedRecipes()
            .onSuccess { list ->
                log { "Fetched ${list.size} cached recipes from the database." }
                if (list.isEmpty()) return@onSuccess
                log { "Returning ${list.size} cached recipes from the database." }
                return@hardWork Result.success(list.shuffled())
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
                return@hardWork Result.success(models.shuffled())
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

    /**
     * A reactive stream of liked recipe IDs.
     * The UI can collect this Flow to instantly update the "Heart" icon on recipes.
     */
    val likedRecipeIds: Flow<List<Int>> = database
        .observeLikedRecipeIds()
        .getOrDefault(flowOf(emptyList()))

    /**
     * A reactive stream of liked recipes.
     * The UI can collect this Flow to display the saved recipes list.
     */
    val likedRecipes: Flow<List<Recipe>> = database
        .observeLikedRecipes()
        .getOrDefault(flowOf(emptyList()))

    /**
     * Toggles whether a specific recipe is liked by the user.
     */
    suspend fun setRecipeLiked(recipeId: Int, isLiked: Boolean): Result<Unit> = hardWork {
        database.toggleRecipeLike(recipeId, isLiked)
            .onSuccess {
                log { "Successfully updated like state for recipe $recipeId to $isLiked" }
            }.onFailure { e ->
                log { "Failed to update like state for recipe $recipeId. Error: ${e.message}" }
            }
    }

    /**
     * Performs hard work in the IO dispatcher, so that the UI thread is not blocked.
     */
    private suspend fun <T> hardWork(
        block: suspend CoroutineScope.() -> T,
    ): T = withContext(Dispatchers.IO, block)

    /**
     * Logs a message with a "DomainManager" prefix. The message is generated lazily, so that it is only computed if logging is actually needed.
     */
    private fun log(message: () -> String) {
        println("DomainManager: ${message()}")
    }
}
