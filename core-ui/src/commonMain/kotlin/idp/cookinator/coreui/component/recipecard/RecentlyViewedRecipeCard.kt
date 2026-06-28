package idp.cookinator.coreui.component.recipecard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription

private val CARD_WIDTH = 150.dp
private val IMAGE_HEIGHT = 124.dp

@Composable
fun RecentlyViewedRecipeCard(
    item: RecipeUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(Theme.size.s12)

    Column(
        modifier = modifier
            .width(CARD_WIDTH)
            .clip(shape)
            .clickable(onClick = onClick),
    ) {
        AsyncImage(
            model = item.recipe.image,
            contentDescription = ContentDescription.IMAGE,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(IMAGE_HEIGHT)
                .clip(shape)
                .background(Theme.color.neutral.n20, shape),
        )
        SpacerHeight(Theme.size.s8)
        Text(
            text = item.recipe.title,
            style = Theme.typography.bold.label,
            color = Theme.color.neutral.n90,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    RecentlyViewedRecipeCard(
        item = RecipeUiModel.stub,
        onClick = {},
    )
}
