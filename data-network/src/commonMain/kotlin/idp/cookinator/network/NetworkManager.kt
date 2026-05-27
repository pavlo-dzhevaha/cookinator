package idp.cookinator.network

import idp.cookinator.model.Recipe
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
        saveRecipeOnServer(response.recipes)
    }

    /**
     * Fetches cached recipes from the local Supabase database.
     *
     * @return A [Result] containing a list of [Recipe] on success, or an [Exception] on failure.
     */
    suspend fun getServerRecipes(): Result<RandomRecipesResponse> = runCatching {
        val recipes = supabase.postgrest["recipes"]
            .select()
            .decodeList<RecipeResponse>()
        RandomRecipesResponse(recipes)
    }

    /**
     * Caches the given list of recipes locally in the Supabase database.
     *
     * @param recipes The list of [Recipe] to be cached.
     */
    private suspend fun saveRecipeOnServer(
        recipes: List<RecipeResponse>,
    ) = runCatching {
        supabase.postgrest["recipes"].upsert(recipes)
    }

    /**
     * Generic function to perform an API request and handle the response.
     */
    private suspend inline fun <reified T> request(
        crossinline block: suspend HttpClient.() -> HttpResponse,
    ): Result<T> = runCatching {
        val response = httpClient.block()
        if (response.status.isSuccess()) {
            response.body<T>()
        } else {
            error("API Error: ${response.status.description}")
        }
    }

    /**
     * Extension function to add the API key to the request headers for authentication.
     */
    private fun HttpRequestBuilder.authorized() {
        header("x-api-key", apiKey)
    }
}
