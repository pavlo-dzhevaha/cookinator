package idp.cookinator.feature.onboarding.screen.welcome.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.viewmodel.base.BaseState

internal data class State(
    val isLoading: Boolean,
    val locale: ThemeLocale,
) : BaseState {
    companion object {
        val initialState = State(
            isLoading = false,
            locale = ThemeLocale.default,
        )
    }
}
