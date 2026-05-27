package idp.cookinator.host.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.base.BaseState

internal data class State(
    val locale: ThemeLocale,
    val style: ThemeStyle,
) : BaseState {
    companion object {
        val initialState = State(
            locale = ThemeLocale.default,
            style = ThemeStyle.default,
        )
    }
}
