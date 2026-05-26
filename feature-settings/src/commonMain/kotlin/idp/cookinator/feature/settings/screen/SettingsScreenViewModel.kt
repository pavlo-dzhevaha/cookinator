package idp.cookinator.feature.settings.screen

import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.BaseViewModel
import idp.cookinator.feature.settings.screen.contract.Event
import idp.cookinator.feature.settings.screen.contract.Intent
import idp.cookinator.feature.settings.screen.contract.State
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.flow.collectLatest

internal class SettingsScreenViewModel(
    private val appStorage: AppStorage,
) : BaseViewModel<State, Intent, Event>(State.initialState) {
    init {
        observeTheme()
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.ChangeTheme -> onChangeTheme(intent.theme)
        }
    }

    private fun onChangeTheme(theme: ThemeStyle) = launch {
        appStorage.setCurrentThemeStyle(theme.name)
    }

    private fun observeTheme() = launch {
        appStorage.observeCurrentThemeStyle().collectLatest { raw ->
            val theme = ThemeStyle.fromStringOrDefault(raw)
            updateState {
                it.copy(
                    theme = theme,
                )
            }
        }
    }
}
