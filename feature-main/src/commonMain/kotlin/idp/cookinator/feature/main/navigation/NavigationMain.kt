package idp.cookinator.feature.main.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.main.screen.main.MainScreen
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationMain

/**
 * Graph for the main navigation host. This is the entry point of the main feature and contains
 * the bottom bar and the main screens.
 */
fun NavigationMain.graph(
    navigator: Navigator,
): NavEntry<NavKey> = when (this) {
    NavigationMain.Main -> NavEntry(this) {
        MainScreen(
            navigator = navigator,
        )
    }
}
