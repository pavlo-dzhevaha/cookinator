package idp.cookinator.preferences

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings

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

    suspend fun clear() = storage.clear()
}
