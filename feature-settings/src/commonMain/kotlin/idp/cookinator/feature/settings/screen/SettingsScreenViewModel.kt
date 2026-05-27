package idp.cookinator.feature.settings.screen

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.feature.settings.screen.contract.Event
import idp.cookinator.feature.settings.screen.contract.Intent
import idp.cookinator.feature.settings.screen.contract.State
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class SettingsScreenViewModel(
    private val appStorage: AppStorage,
) : MviViewModel<State, Intent, Event>(State.initialState) {
    init {
        observeThemeStyle()
        observeThemeLocale()
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.ChangeStyle -> onChangeStyle(intent.style)
            is Intent.ChangeLocale -> onChangeLocale(intent.locale)
        }
    }

    private fun onChangeStyle(theme: ThemeStyle) = launch {
        appStorage.setThemeStyle(theme.name)
    }

    private fun onChangeLocale(locale: ThemeLocale) = launch {
        appStorage.setLanguage(locale.name)
    }

    private fun observeThemeStyle() = launch {
        appStorage
            .observeCurrentThemeStyle()
            .distinctUntilChanged()
            .map(ThemeStyle::parse)
            .collectLatest { style ->
                updateState {
                    it.copy(
                        style = style,
                    )
                }
            }
    }

    private fun observeThemeLocale() = launch {
        appStorage
            .observeLanguageTag()
            .distinctUntilChanged()
            .map(ThemeLocale::parse)
            .collectLatest { locale ->
                updateState {
                    it.copy(
                        locale = locale,
                    )
                }
            }
    }
}
