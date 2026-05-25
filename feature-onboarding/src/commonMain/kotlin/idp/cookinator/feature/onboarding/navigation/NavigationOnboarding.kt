package idp.cookinator.feature.onboarding.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.onboarding.screen.welcome.WelcomeScreen

fun NavigationOnboarding.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = when (this) {
    NavigationOnboarding.Welcome -> NavEntry(this) {
        WelcomeScreen(
            backStack = backStack,
        )
    }
}
