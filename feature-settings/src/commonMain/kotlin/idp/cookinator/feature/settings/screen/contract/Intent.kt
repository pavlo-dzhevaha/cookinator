package idp.cookinator.feature.settings.screen.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.viewmodel.base.BaseIntent

internal sealed interface Intent : BaseIntent {
    data class ChangeStyle(val style: ThemeStyle) : Intent

    data class ChangeLocale(val locale: ThemeLocale) : Intent
}
