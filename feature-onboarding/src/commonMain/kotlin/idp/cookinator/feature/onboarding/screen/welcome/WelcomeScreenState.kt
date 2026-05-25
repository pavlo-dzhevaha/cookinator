package idp.cookinator.feature.onboarding.screen.welcome

data class WelcomeScreenState(
    val isLoading: Boolean,
) {
    companion object {
        val initial = WelcomeScreenState(
            isLoading = false,
        )
    }
}