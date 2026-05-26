package idp.cookinator.feature.main.screen.notification

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import idp.cookinator.feature.navigation.extension.Navigator

@Composable
internal fun NotificationsScreen(
    navigator: Navigator,
    bottomBarHeight: Dp,
) {
    NotificationsContent(
        bottomBarHeight = bottomBarHeight,
    )
}
