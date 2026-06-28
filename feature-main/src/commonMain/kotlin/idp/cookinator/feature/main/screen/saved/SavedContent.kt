package idp.cookinator.feature.main.screen.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.saved_title
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.component.grid.AdaptiveLazyVerticalGrid
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.component.recipecard.RecipeCard
import idp.cookinator.coreui.model.RecipeUiModel
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
    Column {
        AppTopBar(
            title = stringResource(Res.string.saved_title),
        )
        AdaptiveLazyVerticalGrid(
            bottomInset = bottomBarHeight,
            modifier = modifier,
        ) {
            items(
                items = state.items,
                key = { it.recipe.id },
            ) { item ->
                RecipeCard(
                    item = item,
                    imageHeight = Dp.Unspecified,
                    onClick = { onIntent(SavedIntent.OnRecipeClick(item)) },
                    onLike = { onIntent(SavedIntent.OnToggleSaved(item)) },
                    modifier = Modifier.animateItem(),
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
