package idp.cookinator.coreui.component.appbottombar.model

import androidx.compose.ui.graphics.vector.ImageVector
import idp.cookinator.coreui.vector.BookmarkActive
import idp.cookinator.coreui.vector.BookmarkInactive
import idp.cookinator.coreui.vector.HomeActive
import idp.cookinator.coreui.vector.HomeInactive
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.NotificationActive
import idp.cookinator.coreui.vector.NotificationInactive
import idp.cookinator.coreui.vector.ProfileActive
import idp.cookinator.coreui.vector.ProfileInactive

/**
 * Represents the different elements that can be displayed in the bottom bar of the main screen.
 * Each element has an active and inactive icon, as well as a navigation key that corresponds to
 * the screen it should navigate to when selected.
 */
enum class BottomBarElement {
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
}
