package idp.cookinator.feature.main.screen.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.saved_title
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.isPortrait
import idp.cookinator.feature.main.screen.home.components.HomeTrendingItem
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel
import idp.cookinator.feature.main.screen.saved.contract.SavedIntent
import idp.cookinator.feature.main.screen.saved.contract.SavedState
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SavedContent(
    modifier: Modifier = Modifier,
    state: SavedState,
    onIntent: (SavedIntent) -> Unit,
    bottomBarHeight: Dp,
) {
    val size = LocalWindowInfo.current.containerSize
    val longPortrait = size.height > size.width * 2
    val longLandscape = size.width > size.height * 1.5
    val isPortrait = isPortrait()

    Column {
        AppTopBar(
            title = stringResource(Res.string.saved_title),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(
                count = when {
                    isPortrait && longPortrait -> 1
                    isPortrait -> 2
                    longLandscape -> 4
                    else -> 3
                }
            ),
            contentPadding = PaddingValues(
                start = Theme.size.s16,
                end = Theme.size.s16,
                bottom = bottomBarHeight + Theme.size.s16,
            ),
            verticalArrangement = Arrangement.spacedBy(Theme.size.s16),
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s16),
            modifier = modifier
                .fillMaxSize(),
        ) {
            items(state.items) { item ->
                HomeTrendingItem(
                    item = item,
                    imageHeight = Dp.Unspecified,
                    onClick = { onIntent(SavedIntent.OnRecipeClick(item)) },
                    onLike = { onIntent(SavedIntent.OnToggleSaved(item)) },
                    modifier = Modifier
                        .animateItem(),
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    SavedContent(
        state = SavedState.initialState.copy(
            items = RecipeUiModel.stubs,
        ),
        bottomBarHeight = Dp.Hairline,
        onIntent = {},
    )
}
