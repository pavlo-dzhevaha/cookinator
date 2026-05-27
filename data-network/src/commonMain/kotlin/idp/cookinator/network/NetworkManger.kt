package idp.cookinator.network

import idp.cookinator.network.model.RandomRecipesResponse
import idp.cookinator.network.model.RecipeResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * NetworkManager is responsible for handling all network operations related to the Spoonacular API.
 *
 * It provides methods to fetch random recipes and can be extended to include more API endpoints as needed.
 *
 * @property httpClient The [HttpClient] instance used for making network requests.
 */
class NetworkManager(
    private val httpClient: HttpClient,
    private val supabase: SupabaseClient,
) {
    /**
     * API key for Spoonacular API, stored securely using BuildKonfig.
     */
    private val apiKey = BuildKonfig.API_KEY

    /**
     * Base URL for the Spoonacular API.
     */
    private val baseUrl = "https://api.spoonacular.com"

    /**
     * Fetches a random recipes from the Spoonacular API.
     *
     * @return A [Result] containing a [RandomRecipesResponse] on success, or an [Exception] on failure.
     */
    suspend fun getRandomRecipes(): Result<RandomRecipesResponse> = request<RandomRecipesResponse> {
        get("$baseUrl/recipes/random") {
            // Authentication
            authorized()

            // Query Parameters
            parameter("number", 100)
            parameter("includeNutrition", false)
            parameter("language", "ukrainian")
        }
    }.onSuccess { response ->
        cacheRecipeLocally(response.recipes)
    }

    /**
     * Fetches cached recipes from the local Supabase database.
     *
     * @return A [Result] containing a list of [RecipeResponse] on success, or an [Exception] on failure.
     */
    suspend fun getCachedRecipes(): Result<RandomRecipesResponse> = try {
        val recipes = supabase.postgrest["recipes"]
            .select()
            .decodeList<RecipeResponse>()
        Result.success(RandomRecipesResponse(recipes))
    } catch (e: Exception) {
        Result.failure(e)
    }

    /**
     * Caches the given list of recipes locally in the Supabase database.
     *
     * @param recipes The list of [RecipeResponse] to be cached.
     */
    private suspend fun cacheRecipeLocally(
        recipes: List<RecipeResponse>,
    ) {
        withContext(Dispatchers.IO) {
            try {
                // Upsert will insert the recipe, or update it if the ID already exists
                val result = supabase.postgrest["recipes"].upsert(recipes)
                println("Cached recipes locally: ${result.data}")
            } catch (e: Exception) {
                // TODO log
                println("Failed to cache recipes locally: ${e.message}")
            }
        }
    }

    /**
     * Generic function to perform an API request and handle the response.
     */
    private suspend inline fun <reified T> request(
        crossinline block: suspend HttpClient.() -> HttpResponse,
    ): Result<T> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = httpClient.block()
            if (response.status.isSuccess()) {
                Result.success(response.body<T>())
            } else {
                Result.failure(Exception("API Error: ${response.status.description}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Extension function to add the API key to the request headers for authentication.
     */
    private fun HttpRequestBuilder.authorized() {
        header("x-api-key", apiKey)
    }
}
