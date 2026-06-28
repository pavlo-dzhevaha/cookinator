package idp.cookinator.domain.recipe

import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.domain.internal.useCaseIo
import idp.cookinator.domain.internal.useCaseLog
import idp.cookinator.model.Recipe
import idp.cookinator.network.NetworkManager
import idp.cookinator.network.model.RandomRecipesResponse
import idp.cookinator.network.model.toDomainModels

internal class SyncDiscoveryRecipesUseCaseImpl(
    private val network: NetworkManager,
    private val database: RecipeDatabaseManager,
    private val orderStore: DiscoveryOrderStore,
) : SyncDiscoveryRecipesUseCase {
    override suspend fun invoke(forceRefresh: Boolean): Result<Unit> = useCaseIo {
        val recipeCount = database.getRecipeCount().getOrElse { error ->
            log { "Failed to read recipe count from the database. Error: ${error.message}" }
            return@useCaseIo Result.failure(error)
        }

        if (forceRefresh && recipeCount > 0) {
            return@useCaseIo reshuffleLocalOrder()
        }

        if (!forceRefresh && recipeCount > 0) {
            if (orderStore.getOrder().isNullOrEmpty()) {
                log { "Room has $recipeCount recipes but no discovery order; generating shuffle." }
                return@useCaseIo reshuffleLocalOrder()
            }
            log { "Room already has $recipeCount recipes; skipping network sync." }
            return@useCaseIo Result.success(Unit)
        }

        fetchFromRemoteAndPersist()
    }

    private suspend fun reshuffleLocalOrder(): Result<Unit> {
        return database
            .getAllCachedRecipes()
            .mapCatching { recipes ->
                if (recipes.isEmpty()) {
                    log { "Cannot reshuffle discovery order because Room has no recipes." }
                    return@mapCatching
                }
                val order = recipes.map(Recipe::id).shuffled()
                orderStore.setOrder(order)
                log { "Reshuffled discovery order for ${order.size} recipes." }
            }
    }

    private suspend fun fetchFromRemoteAndPersist(): Result<Unit> {
        val supabaseResult = network
            .getServerRecipes()
            .map(RandomRecipesResponse::toDomainModels)

        supabaseResult
            .onSuccess { models ->
                log { "Fetched ${models.size} recipes from Supabase." }
            }.onFailure { error ->
                log { "Failed to fetch recipes from Supabase. Error: ${error.message}" }
            }

        val supabaseModels = supabaseResult.getOrNull()
        if (!supabaseModels.isNullOrEmpty()) {
            return persistAndShuffle(supabaseModels, "Supabase")
        }

        return network
            .getRandomRecipes()
            .map(RandomRecipesResponse::toDomainModels)
            .fold(
                onSuccess = { models ->
                    log { "Fetched ${models.size} recipes from Spoonacular." }
                    if (models.isEmpty()) {
                        Result.failure(IllegalStateException("No recipes returned from remote sources."))
                    } else {
                        persistAndShuffle(models, "Spoonacular")
                    }
                },
                onFailure = { error ->
                    log { "Failed to fetch recipes from Spoonacular. Error: ${error.message}" }
                    Result.failure(error)
                },
            )
    }

    private suspend fun persistAndShuffle(
        models: List<Recipe>,
        source: String,
    ): Result<Unit> {
        return database
            .saveRecipes(models)
            .mapCatching {
                log { "Saved ${models.size} recipes from $source to Room." }
                val order = models.map(Recipe::id).shuffled()
                orderStore.setOrder(order)
                log { "Persisted discovery order for ${order.size} recipes." }
            }
            .onFailure { error ->
                log { "Failed to save recipes from $source to Room. Error: ${error.message}" }
            }
    }

    private fun log(message: () -> String) {
        useCaseLog(TAG, message)
    }

    private companion object {
        const val TAG = "SyncDiscoveryRecipesUseCase"
    }
}
