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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_trending_action
import cookinator.localisation.generated.resources.home_trending_title
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.ArrowRight
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.navigation.features.HomeRecipeSection
import idp.cookinator.coreui.component.recipecard.RecipeCard
import idp.cookinator.coreui.model.RecipeUiModel
import org.jetbrains.compose.resources.stringResource

const val IMAGE_HEIGHT_RATIO = 180f / 812f

@Composable
internal fun HomeTrending(
    items: List<RecipeUiModel>,
    onIntent: (HomeIntent) -> Unit,
) {
    val screenSize = LocalWindowInfo.current.containerDpSize
    val imageHeight = remember(screenSize) {
        screenSize.height * IMAGE_HEIGHT_RATIO
    }

    Column(
        modifier = Modifier
            .padding(vertical = Theme.size.s12)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = Theme.size.s20),
        ) {
            Text(
                text = stringResource(Res.string.home_trending_title),
                style = Theme.typography.bold.h5,
                color = Theme.color.neutral.n90,
                modifier = Modifier
                    .weight(1f),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Theme.size.s4),
                modifier = Modifier
                    .clickable { onIntent(HomeIntent.OnSeeAllClick(HomeRecipeSection.Trending)) }
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
            items(items) { item ->
                RecipeCard(
                    item = item,
                    imageHeight = imageHeight,
                    onClick = { onIntent(HomeIntent.OnRecipeClick(item)) },
                    onLike = { onIntent(HomeIntent.OnToggleSaved(item)) },
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    HomeTrending(
        items = RecipeUiModel.stubs,
        onIntent = {},
    )
}
