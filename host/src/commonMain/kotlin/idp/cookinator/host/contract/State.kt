package idp.cookinator.host.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle

internal data class State(
    val locale: ThemeLocale,
    val style: ThemeStyle,
) {
    companion object {
        val initialState = State(
            locale = ThemeLocale.default,
            style = ThemeStyle.default,
        )
    }
}
