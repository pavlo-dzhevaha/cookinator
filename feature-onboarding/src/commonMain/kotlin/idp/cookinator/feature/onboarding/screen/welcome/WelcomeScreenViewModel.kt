package idp.cookinator.feature.onboarding.screen.welcome

import idp.cookinator.coreui.viewmodel.BaseViewModel
import idp.cookinator.feature.onboarding.screen.welcome.contract.WelcomeScreenEvent
import idp.cookinator.feature.onboarding.screen.welcome.contract.WelcomeScreenIntent
import idp.cookinator.feature.onboarding.screen.welcome.contract.WelcomeScreenState
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class WelcomeScreenViewModel(
    private val storage: AppStorage
) : BaseViewModel<WelcomeScreenState, WelcomeScreenIntent, WelcomeScreenEvent>(WelcomeScreenState.initial) {
    override fun onIntent(intent: WelcomeScreenIntent) = launch {
        when (intent) {
            WelcomeScreenIntent.OnContinue -> onContinue()
        }
    }

    private suspend fun onContinue() {
        updateState { it.copy(isLoading = true) }
        storage.setOnboardingCompleted(true)
        delay(1.seconds) // Simulating hard work
        sendEvent(WelcomeScreenEvent.Continue)
    }
}