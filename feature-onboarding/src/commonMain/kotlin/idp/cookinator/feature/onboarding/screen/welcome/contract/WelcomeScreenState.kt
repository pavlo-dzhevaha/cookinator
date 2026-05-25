package idp.cookinator.feature.onboarding.screen.welcome.contract

data class WelcomeScreenState(
    val isLoading: Boolean,
) {
    companion object {
        val initial = WelcomeScreenState(
            isLoading = false,
        )
    }
}