package idp.cookinator.preferences

import com.russhwolf.settings.Settings

class AppStorage(
    private val storage: Settings,
) {
    private val onboardingKey = "onboarding_completed"

    fun isOnboardingCompleted(): Boolean = storage.getBoolean(onboardingKey, false)

    fun setOnboardingCompleted(completed: Boolean) = storage.putBoolean(onboardingKey, completed)

    fun clear() = storage.clear()
}
