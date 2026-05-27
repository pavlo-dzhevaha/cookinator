package idp.cookinator.host

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.StateViewModel
import idp.cookinator.host.contract.State
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class AppViewModel(
    private val storage: AppStorage,
) : StateViewModel<State>(State.initialState) {
    init {
        observeThemeStyle()
        observeThemeLocale()
    }

    private fun observeThemeStyle() = launch {
        storage
            .observeCurrentThemeStyle()
            .distinctUntilChanged()
            .map(ThemeStyle::parse)
            .collectLatest { style ->
                updateState {
                    it.copy(style = style)
                }
            }
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
