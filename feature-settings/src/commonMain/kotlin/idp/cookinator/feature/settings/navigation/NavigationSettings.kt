package idp.cookinator.feature.settings.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationSettings
import idp.cookinator.feature.settings.screen.SettingsScreen

fun NavigationSettings.graph(
    navigator: Navigator,
): NavEntry<NavKey> = when (this) {
    NavigationSettings.Settings -> NavEntry(this) {
        SettingsScreen(navigator = navigator)
    }
}
