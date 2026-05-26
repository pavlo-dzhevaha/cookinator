package idp.cookinator.preferences

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings

/**
 * A wrapper around [FlowSettings] to provide a more convenient API for the app's specific needs.
 *
 * TODO internal impl with public interface
 */
@OptIn(ExperimentalSettingsApi::class)
class AppStorage(
    private val storage: FlowSettings,
) {
    private val onboardingKey = "onboarding_completed"
    private val themeStyleKey = "theme_style"

    suspend fun isOnboardingCompleted(): Boolean = storage.getBoolean(onboardingKey, false)

    suspend fun setOnboardingCompleted(completed: Boolean) =
        storage.putBoolean(onboardingKey, completed)

    fun observeCurrentThemeStyle() = storage.getStringFlow(themeStyleKey, "")

    suspend fun setCurrentThemeStyle(themeStyle: String) =
        storage.putString(themeStyleKey, themeStyle)

    suspend fun clear() = storage.clear()
}
