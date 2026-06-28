package idp.cookinator.coreui.component.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.styling.theme.Theme

@Composable
fun rememberAdaptiveGridColumnCount(): Int {
    val (width, height) = LocalWindowInfo.current.containerDpSize
    return remember(width, height) {
        val portrait = height > width
        val longPortrait = height > width * 2
        val longLandscape = width > height * 1.5f
        when {
            portrait && longPortrait -> 1
            portrait -> 2
            longLandscape -> 4
            else -> 3
        }
    }
}

@Composable
fun AdaptiveLazyVerticalGrid(
    modifier: Modifier = Modifier,
    bottomInset: Dp = Dp.Hairline,
    horizontalPadding: Dp = Theme.size.s16,
    content: LazyGridScope.() -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(rememberAdaptiveGridColumnCount()),
        contentPadding = PaddingValues(
            start = horizontalPadding,
            end = horizontalPadding,
            bottom = bottomInset + Theme.size.s16,
        ),
        verticalArrangement = Arrangement.spacedBy(Theme.size.s16),
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s16),
        modifier = modifier.fillMaxSize(),
        content = content,
    )
}
