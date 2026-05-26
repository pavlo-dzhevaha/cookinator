package idp.cookinator.feature.settings.screen.contract

import idp.cookinator.coreui.styling.theme.ThemeStyle

internal sealed interface Intent {
    data class ChangeTheme(val theme: ThemeStyle) : Intent
}
