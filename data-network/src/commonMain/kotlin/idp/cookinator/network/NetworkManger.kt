package idp.cookinator.network

import idp.cookinator.network.model.RandomRecipesResponse
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
    private val httpClient: HttpClient
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
    suspend fun getRandomRecipes(): Result<RandomRecipesResponse> = request {
        get("$baseUrl/recipes/random") {
            // Authentication
            authorized()

            // Query Parameters
            parameter("number", 1)
            parameter("includeNutrition", false)
            parameter("language", "ukrainian")
        }
    }

    /**
     * Generic function to perform an API request and handle the response.
     */
    private suspend inline fun <reified T> request(block: HttpClient.() -> HttpResponse): Result<T> =
        try {
            val response = httpClient.block()
            if (response.status.isSuccess()) {
                Result.success(response.body<T>())
            } else {
                Result.failure(Exception("API Error: ${response.status.description}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    /**
     * Extension function to add the API key to the request headers for authentication.
     */
    private fun HttpRequestBuilder.authorized() {
        header("x-api-key", apiKey)
    }
}
