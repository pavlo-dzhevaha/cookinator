package idp.cookinator.feature.main.navigation.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.feature.main.screen.home.HomeScreen
import idp.cookinator.feature.main.screen.profile.ProfileScreen
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

internal val internalConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            screen<NavigationMainInternal.Home>()
            screen<NavigationMainInternal.Saved>()
            screen<NavigationMainInternal.Notifications>()
            screen<NavigationMainInternal.Profile>()
        }
    }
}

@Serializable
internal sealed interface NavigationMainInternal : NavKey {
    @Serializable
    data object Home : NavigationMainInternal

    @Serializable
    data object Saved : NavigationMainInternal

    @Serializable
    data object Notifications : NavigationMainInternal

    @Serializable
    data object Profile : NavigationMainInternal

    private companion object
}

internal fun NavigationMainInternal.graph(
    navigator: Navigator,
    bottomBarHeight: Dp,
): NavEntry<NavKey> = when (this) {
    NavigationMainInternal.Home -> NavEntry(this) {
        HomeScreen(
            navigator = navigator,
            bottomBarHeight = bottomBarHeight,
        )
    }

    NavigationMainInternal.Saved -> NavEntry(this) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Saved")
        }
    }

    NavigationMainInternal.Notifications -> NavEntry(this) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Notifications")
        }
    }

    NavigationMainInternal.Profile -> NavEntry(this) {
        ProfileScreen(
            navigator = navigator,
        )
    }
}
