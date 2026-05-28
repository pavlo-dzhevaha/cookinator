package idp.cookinator.feature.main.navigation.internal

import androidx.compose.ui.unit.Dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.feature.main.screen.home.HomeScreen
import idp.cookinator.feature.main.screen.notification.NotificationsScreen
import idp.cookinator.feature.main.screen.profile.ProfileScreen
import idp.cookinator.feature.main.screen.saved.SavedScreen
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
    internalNavigator: Navigator,
    bottomBarHeight: Dp,
): NavEntry<NavKey> = when (this) {
    NavigationMainInternal.Home -> NavEntry(this) {
        HomeScreen(
            navigator = navigator,
            bottomBarHeight = bottomBarHeight,
        )
    }

    NavigationMainInternal.Saved -> NavEntry(this) {
        SavedScreen(
            internalNavigator = internalNavigator,
            bottomBarHeight = bottomBarHeight,
        )
    }

    NavigationMainInternal.Notifications -> NavEntry(this) {
        NotificationsScreen(
            navigator = navigator,
            bottomBarHeight = bottomBarHeight,
        )
    }

    NavigationMainInternal.Profile -> NavEntry(this) {
        ProfileScreen(
            navigator = navigator,
            bottomBarHeight = bottomBarHeight,
        )
    }
}
