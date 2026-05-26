package idp.cookinator.feature.settings.screen.contract

import idp.cookinator.coreui.styling.theme.ThemeStyle

internal data class State(
    val theme: ThemeStyle,
) {
    companion object {
        val initialState = State(
            theme = ThemeStyle.default,
        )
    }
}
