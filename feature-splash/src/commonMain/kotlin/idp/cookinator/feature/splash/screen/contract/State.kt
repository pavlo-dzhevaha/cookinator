package idp.cookinator.feature.splash.screen.contract

internal data class State(
    val loadingToLong: Boolean,
) {
    companion object {
        val initial = State(
            loadingToLong = false,
        )
    }
}
