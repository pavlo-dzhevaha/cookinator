package idp.cookinator.feature.main.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_title
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.realImePadding
import idp.cookinator.feature.main.screen.home.components.HomeSearch
import idp.cookinator.feature.main.screen.home.components.HomeTrending
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    bottomBarHeight: Dp,
    onIntent: (HomeIntent) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = bottomBarHeight + Theme.size.s16,
        ),
        overscrollEffect = null,
        modifier = modifier
            .fillMaxSize()
            .realImePadding(bottomBarHeight),
    ) {
        item {
            AppTopBar(
                title = stringResource(Res.string.home_title),
            )
        }
        stickyHeader {
            HomeSearch(
                query = state.query,
                onValueChange = { onIntent(HomeIntent.OnSearchQueryChange(it)) },
            )
        }
        item {
            HomeTrending(
                items = state.trending,
                onIntent = onIntent,
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    HomeContent(
        bottomBarHeight = Dp.Hairline,
        state = HomeState.initialState.copy(
            trending = RecipeUiModel.stubs,
        ),
        onIntent = {},
    )
}
