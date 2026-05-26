package idp.cookinator.feature.home.screen.components.bottombar.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.vector.BookmarkActive
import idp.cookinator.coreui.vector.BookmarkInactive
import idp.cookinator.coreui.vector.HomeActive
import idp.cookinator.coreui.vector.HomeInactive
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.NotificationActive
import idp.cookinator.coreui.vector.NotificationInactive
import idp.cookinator.coreui.vector.ProfileActive
import idp.cookinator.coreui.vector.ProfileInactive
import idp.cookinator.feature.home.navigation.internal.NavigationHostInternal

internal enum class BottomBarElement {
    Home,
    Saved,
    Notifications,
    Profile;

    val iconActive: ImageVector
        get() = when (this) {
            Home -> Icons.HomeActive
            Saved -> Icons.BookmarkActive
            Notifications -> Icons.NotificationActive
            Profile -> Icons.ProfileActive
        }

    val iconInactive: ImageVector
        get() = when (this) {
            Home -> Icons.HomeInactive
            Saved -> Icons.BookmarkInactive
            Notifications -> Icons.NotificationInactive
            Profile -> Icons.ProfileInactive
        }

    val navigationKey: NavKey
        get() = when (this) {
            Home -> NavigationHostInternal.Home
            Saved -> NavigationHostInternal.Saved
            Notifications -> NavigationHostInternal.Notifications
            Profile -> NavigationHostInternal.Profile
        }
}
