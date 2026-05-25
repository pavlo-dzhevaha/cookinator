package idp.cookinator.feature.home.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.features.NavigationHome

fun NavigationHome.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = when (this) {
    NavigationHome.Home -> NavEntry(this) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Text("Home")
        }
    }
}
