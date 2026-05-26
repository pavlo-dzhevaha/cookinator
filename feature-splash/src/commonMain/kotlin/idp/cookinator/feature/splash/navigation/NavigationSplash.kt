package idp.cookinator.feature.splash.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationSplash
import idp.cookinator.feature.splash.screen.SplashScreen

fun NavigationSplash.graph(
    navigator: Navigator,
): NavEntry<NavKey> = when (this) {
    NavigationSplash.Splash -> NavEntry(this) {
        SplashScreen(navigator = navigator)
    }
}
