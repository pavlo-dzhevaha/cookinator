package idp.cookinator.feature.main.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_recently_viewed_title
import cookinator.localisation.generated.resources.home_trending_action
import idp.cookinator.coreui.component.recipecard.RecentlyViewedRecipeCard
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.ArrowRight
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.navigation.features.AllRecipesFilter
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeRecentlyViewed(
    items: List<RecipeUiModel>,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) return

    Column(
        modifier = modifier.padding(vertical = Theme.size.s12),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = Theme.size.s20),
        ) {
            Text(
                text = stringResource(Res.string.home_recently_viewed_title),
                style = Theme.typography.bold.h5,
                color = Theme.color.neutral.n90,
                modifier = Modifier.weight(1f),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Theme.size.s4),
                modifier = Modifier.clickable {
                    onIntent(HomeIntent.OnSeeAllClick(AllRecipesFilter.RecentlyViewed))
                },
            ) {
                Text(
                    text = stringResource(Res.string.home_trending_action),
                    style = Theme.typography.bold.label,
                    color = Theme.color.primary.p50,
                )
                Icon(
                    imageVector = Icons.ArrowRight,
                    contentDescription = ContentDescription.ICON,
                    tint = Theme.color.primary.p50,
                )
            }
        }
        SpacerHeight(Theme.size.s16)
        LazyRow(
            contentPadding = PaddingValues(horizontal = Theme.size.s20),
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s16),
        ) {
            items(
                items = items,
                key = { it.recipe.id },
            ) { item ->
                RecentlyViewedRecipeCard(
                    item = item,
                    onClick = { onIntent(HomeIntent.OnRecipeClick(item)) },
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    HomeRecentlyViewed(
        items = RecipeUiModel.stubs,
        onIntent = {},
    )
}
