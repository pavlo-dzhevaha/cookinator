package idp.cookinator.preferences

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A wrapper around [FlowSettings] to provide a more convenient API for the app's specific needs.
 */
@OptIn(ExperimentalSettingsApi::class)
class AppStorage(
    private val storage: FlowSettings,
) {
    private val onboardingKey = "onboarding_completed"
    private val themeStyleKey = "theme_style"
    private val languageKey = "app_language"
    private val recipeCreateDraftKey = "recipe_create_draft"
    private val discoveryOrderKey = "discovery_recipe_order"

    suspend fun isOnboardingCompleted(): Boolean = storage.getBoolean(onboardingKey, false)

    suspend fun setOnboardingCompleted(completed: Boolean) =
        storage.putBoolean(onboardingKey, completed)

    fun observeCurrentThemeStyle() = storage.getStringFlow(themeStyleKey, "")

    suspend fun setThemeStyle(themeStyle: String) = storage.putString(themeStyleKey, themeStyle)

    fun observeLanguageTag() = storage.getStringFlow(languageKey, "")

    suspend fun setLanguage(languageTag: String) = storage.putString(languageKey, languageTag)

    suspend fun getRecipeCreateDraftJson(): String? =
        storage.getString(recipeCreateDraftKey, "").ifBlank { null }

    suspend fun setRecipeCreateDraftJson(json: String?) {
        if (json.isNullOrBlank()) {
            storage.remove(recipeCreateDraftKey)
        } else {
            storage.putString(recipeCreateDraftKey, json)
        }
    }

    suspend fun getDiscoveryRecipeOrder(): List<Int>? =
        storage
            .getString(discoveryOrderKey, "")
            .ifBlank { null }
            ?.split(",")
            ?.mapNotNull { it.toIntOrNull() }
            ?.takeIf { it.isNotEmpty() }

    suspend fun setDiscoveryRecipeOrder(ids: List<Int>?) {
        if (ids.isNullOrEmpty()) {
            storage.remove(discoveryOrderKey)
        } else {
            storage.putString(discoveryOrderKey, ids.joinToString(","))
        }
    }

    fun observeDiscoveryRecipeOrder(): Flow<List<Int>> =
        storage.getStringFlow(discoveryOrderKey, "").map { value ->
            value
                .split(",")
                .mapNotNull { it.toIntOrNull() }
        }

    suspend fun clear() = storage.clear()
}
