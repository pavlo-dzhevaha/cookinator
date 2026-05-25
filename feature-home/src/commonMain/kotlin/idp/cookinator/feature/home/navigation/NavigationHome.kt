package idp.cookinator.feature.home.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.home.screen.components.bottombar.BottomBar
import idp.cookinator.feature.home.screen.components.bottombar.model.BottomBarElement
import idp.cookinator.feature.navigation.features.NavigationHome

fun NavigationHome.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = when (this) {
    NavigationHome.Host -> NavEntry(this) {
        var currentTab by remember { mutableStateOf(BottomBarElement.Home) }
        Scaffold(
            containerColor = Theme.color.system.white,
            bottomBar = {
                BottomBar(
                    selected = currentTab,
                    onItemSelected = { currentTab = it },
                    onAddClick = { /* TODO */ },
                )
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text("Home")
            }
        }
    }
}
