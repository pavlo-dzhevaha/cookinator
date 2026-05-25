package idp.cookinator.feature.onboarding.screen.welcome

import idp.cookinator.coreui.viewmodel.BaseViewModel
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class WelcomeScreenViewModel(
    private val storage: AppStorage
) : BaseViewModel<WelcomeScreenState>(WelcomeScreenState.initial) {
    suspend fun onContinue() {
        updateState { it.copy(isLoading = true) }
        storage.setOnboardingCompleted(true)
        delay(1.seconds) // Simulating hard work
        // Send event
    }
}