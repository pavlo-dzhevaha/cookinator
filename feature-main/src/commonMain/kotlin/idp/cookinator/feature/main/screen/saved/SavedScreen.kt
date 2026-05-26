package idp.cookinator.feature.main.screen.saved

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import idp.cookinator.feature.navigation.extension.Navigator

@Composable
internal fun SavedScreen(
    navigator: Navigator,
    bottomBarHeight: Dp,
) {
    SavedContent(
        bottomBarHeight = bottomBarHeight,
    )
}
