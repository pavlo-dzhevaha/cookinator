package idp.cookinator.feature.onboarding.screen.welcome

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.feature.onboarding.screen.welcome.contract.Event
import idp.cookinator.feature.onboarding.screen.welcome.contract.Intent
import idp.cookinator.feature.onboarding.screen.welcome.contract.State
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.seconds

internal class WelcomeScreenViewModel(
    private val storage: AppStorage
) : MviViewModel<State, Intent, Event>(State.initialState) {
    init {
        observeThemeLocale()
    }

    override fun onIntent(intent: Intent) {
        launch {
            when (intent) {
                Intent.OnContinue -> onContinue()
                Intent.OnChangeLocale -> onChangeLocale()
            }
        }
    }

    private suspend fun onContinue() {
        updateState { it.copy(isLoading = true) }
        storage.setOnboardingCompleted(true)
        delay(1.seconds) // Simulating hard work
        sendEvent(Event.Continue)
    }

    private fun onChangeLocale() = launch {
        val currentLocale = state.locale
        val newLocale = when (currentLocale) {
            ThemeLocale.US -> ThemeLocale.UK
            ThemeLocale.UK -> ThemeLocale.US
        }
        storage.setLanguage(newLocale.name)
    }

    private fun observeThemeLocale() = launch {
        storage
            .observeLanguageTag()
            .distinctUntilChanged()
            .map(ThemeLocale::parse)
            .collectLatest { locale ->
                updateState {
                    it.copy(locale = locale)
                }
            }
    }
}