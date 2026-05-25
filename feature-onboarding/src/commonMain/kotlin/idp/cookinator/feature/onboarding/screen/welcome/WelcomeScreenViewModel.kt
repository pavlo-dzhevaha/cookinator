package idp.cookinator.feature.onboarding.screen.welcome

import idp.cookinator.coreui.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class WelcomeScreenViewModel : BaseViewModel<WelcomeScreenState>(
    initialState = WelcomeScreenState.initial,
) {
    suspend fun onContinue() {
        updateState {
            it.copy(
                isLoading = true,
            )
        }
        delay(3.seconds)
        updateState {
            it.copy(
                isLoading = false,
            )
        }
    }
}