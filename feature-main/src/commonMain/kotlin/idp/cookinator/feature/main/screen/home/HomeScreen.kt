package idp.cookinator.feature.main.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import idp.cookinator.feature.navigation.extension.Navigator

@Composable
internal fun HomeScreen(
    navigator: Navigator,
    bottomBarHeight: Dp = Dp.Hairline,
) {
    HomeContent(
        bottomBarHeight = bottomBarHeight,
    )
}