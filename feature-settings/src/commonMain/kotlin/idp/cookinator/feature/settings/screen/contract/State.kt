package idp.cookinator.feature.settings.screen.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.base.BaseState

internal data class State(
    val style: ThemeStyle,
    val locale: ThemeLocale,
) : BaseState {
    companion object {
        val initialState = State(
            style = ThemeStyle.default,
            locale = ThemeLocale.default,
        )
    }
}
