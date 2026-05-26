package idp.cookinator.feature.main.screen.saved

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme

@Composable
internal fun SavedContent(
    modifier: Modifier = Modifier,
    bottomBarHeight: Dp = Dp.Hairline,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = bottomBarHeight + Theme.size.s16,
        ),
        modifier = modifier
            .fillMaxSize(),
    ) {
        stickyHeader {
            AppTopBar(
                title = "Saved recipes",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = AppTheme {
    SavedContent()
}