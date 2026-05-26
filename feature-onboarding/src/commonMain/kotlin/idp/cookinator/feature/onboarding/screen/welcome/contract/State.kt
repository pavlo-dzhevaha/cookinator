package idp.cookinator.feature.onboarding.screen.welcome.contract

internal data class State(
    val isLoading: Boolean,
) {
    companion object {
        val initialState = State(
            isLoading = false,
        )
    }
}