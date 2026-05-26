package idp.cookinator.feature.home.navigation.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.home.screen.home.HomeContent
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(InternalSerializationApi::class)
internal val internalHostSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationHostInternal.Home>()
        screen<NavigationHostInternal.Saved>()
        screen<NavigationHostInternal.Notifications>()
        screen<NavigationHostInternal.Profile>()
    }
}

@Serializable
internal sealed interface NavigationHostInternal : NavKey {
    @Serializable
    data object Home : NavigationHostInternal

    @Serializable
    data object Saved : NavigationHostInternal

    @Serializable
    data object Notifications : NavigationHostInternal

    @Serializable
    data object Profile : NavigationHostInternal
}

internal fun NavigationHostInternal.graph(
    backStack: NavBackStack<NavKey>,
    bottomBarHeight: Dp,
): NavEntry<NavKey> = when (this) {
    NavigationHostInternal.Home -> NavEntry(this) {
        Scaffold { paddingValues ->
            HomeContent(
                modifier = Modifier
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = bottomBarHeight + Theme.size.s16),
            )
        }
    }

    NavigationHostInternal.Saved -> NavEntry(this) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Saved")
        }
    }

    NavigationHostInternal.Notifications -> NavEntry(this) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Notifications")
        }
    }

    NavigationHostInternal.Profile -> NavEntry(this) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Profile")
        }
    }
}
