package idp.cookinator.feature.splash.screen

import idp.cookinator.coreui.viewmodel.BaseViewModel
import idp.cookinator.feature.navigation.features.NavigationMain
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.splash.screen.contract.Event
import idp.cookinator.feature.splash.screen.contract.Intent
import idp.cookinator.feature.splash.screen.contract.State
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

internal class SplashScreenViewModel(
    private val appStorage: AppStorage,
) : BaseViewModel<State, Intent, Event>(State.initialState) {
    init {
        startDestinationLogic()
    }

    override fun onIntent(intent: Intent) = Unit

    private fun startDestinationLogic() = launch {
//        val minimumDelay = launch { delay(2.seconds) }
        val minimumDelay = launch { delay(0.seconds) }
        val onboardingCompleted = appStorage.isOnboardingCompleted()
        minimumDelay.join()

        sendEvent(
            Event.NavigateToStartDestination(
                startDestination = when {
                    onboardingCompleted -> NavigationMain.Main
                    else -> NavigationOnboarding.Welcome
                },
            ),
        )
    }
}
