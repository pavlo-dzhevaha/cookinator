package idp.cookinator.feature.settings.screen.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle

internal sealed interface Intent {
    data class ChangeStyle(val style: ThemeStyle) : Intent

    data class ChangeLocale(val locale: ThemeLocale) : Intent
}
