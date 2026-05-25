package idp.cookinator.feature.splash.screen.contract

import androidx.navigation3.runtime.NavKey

internal sealed interface Event {
    data class NavigateToStartDestination(
        val startDestination: NavKey,
    ) : Event
}
