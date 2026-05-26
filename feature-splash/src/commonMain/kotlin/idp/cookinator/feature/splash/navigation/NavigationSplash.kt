package idp.cookinator.feature.splash.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.features.NavigationSplash
import idp.cookinator.feature.splash.screen.SplashScreen

fun NavigationSplash.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = NavEntry(this) {
    SplashScreen(backStack = backStack)
}
