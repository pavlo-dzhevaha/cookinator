package idp.cookinator.feature.onboarding.screen.welcome.contract

import idp.cookinator.coreui.styling.theme.ThemeLocale

internal data class State(
    val isLoading: Boolean,
    val locale: ThemeLocale,
) {
    companion object {
        val initialState = State(
            isLoading = false,
            locale = ThemeLocale.default,
        )
    }
}
