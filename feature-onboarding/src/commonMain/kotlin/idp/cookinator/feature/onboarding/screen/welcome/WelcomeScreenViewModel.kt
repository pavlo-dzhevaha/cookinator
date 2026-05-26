package idp.cookinator.feature.onboarding.screen.welcome

import idp.cookinator.coreui.viewmodel.BaseViewModel
import idp.cookinator.feature.onboarding.screen.welcome.contract.Event
import idp.cookinator.feature.onboarding.screen.welcome.contract.Intent
import idp.cookinator.feature.onboarding.screen.welcome.contract.State
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

internal class WelcomeScreenViewModel(
    private val storage: AppStorage
) : BaseViewModel<State, Intent, Event>(State.initialState) {
    override fun onIntent(intent: Intent) {
        launch {
            when (intent) {
                Intent.OnContinue -> onContinue()
            }
        }
    }

    private suspend fun onContinue() {
        updateState { it.copy(isLoading = true) }
        storage.setOnboardingCompleted(true)
        delay(1.seconds) // Simulating hard work
        sendEvent(Event.Continue)
    }
}