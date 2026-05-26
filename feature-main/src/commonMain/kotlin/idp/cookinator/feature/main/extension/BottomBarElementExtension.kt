package idp.cookinator.feature.main.extension

import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement.Home
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement.Notifications
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement.Profile
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement.Saved
import idp.cookinator.feature.main.navigation.internal.NavigationMainInternal

/**
 * Returns the navigation key associated with this bottom bar element. This key is used to
 * determine which screen to navigate to when the element is selected.
 */
val BottomBarElement.navigationKey: NavKey
    get() = when (this) {
        Home -> NavigationMainInternal.Home
        Saved -> NavigationMainInternal.Saved
        Notifications -> NavigationMainInternal.Notifications
        Profile -> NavigationMainInternal.Profile
    }
