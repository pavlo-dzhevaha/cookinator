package idp.cookinator.feature.settings.screen.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle

internal data class State(
    val style: ThemeStyle,
    val locale: ThemeLocale,
) {
    companion object {
        val initialState = State(
            style = ThemeStyle.default,
            locale = ThemeLocale.default,
        )
    }
}
