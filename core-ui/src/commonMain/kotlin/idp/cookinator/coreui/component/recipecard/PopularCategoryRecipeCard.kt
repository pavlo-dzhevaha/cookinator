package idp.cookinator.coreui.component.recipecard

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_popular_category_time_label
import cookinator.localisation.generated.resources.home_popular_category_time_value
import idp.cookinator.coreui.component.spacer.SpacerWeight
import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.BookmarkActive
import idp.cookinator.coreui.vector.BookmarkInactive
import idp.cookinator.coreui.vector.Icons
import org.jetbrains.compose.resources.stringResource

private val CARD_WIDTH = 150.dp
private val CARD_BODY_HEIGHT = 176.dp
private val IMAGE_SIZE = 110.dp
private val IMAGE_OVERLAP = IMAGE_SIZE / 2

@Composable
fun PopularCategoryRecipeCard(
    item: RecipeUiModel,
    onClick: () -> Unit,
    onLike: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(Theme.size.s12)

    Box(
        modifier = modifier.width(CARD_WIDTH),
    ) {
        Box(
            modifier = Modifier
                .padding(top = IMAGE_OVERLAP)
                .height(CARD_BODY_HEIGHT)
                .fillMaxWidth()
                .clip(shape)
                .clickable(onClick = onClick)
                .background(Theme.color.neutral.n10, shape),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Theme.size.s12),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IMAGE_OVERLAP),
                )
                Text(
                    text = item.recipe.title,
                    style = Theme.typography.bold.label,
                    color = Theme.color.neutral.n90,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                )
                SpacerWeight()
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Theme.size.s12),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(Res.string.home_popular_category_time_label),
                            style = Theme.typography.regular.small,
                            color = Theme.color.neutral.n30,
                        )
                        Text(
                            text = stringResource(
                                Res.string.home_popular_category_time_value,
                                item.recipe.readyInMinutes.toString(),
                            ),
                            style = Theme.typography.bold.small,
                            color = Theme.color.neutral.n90,
                        )
                    }
                    Crossfade(
                        targetState = if (item.isSaved) Icons.BookmarkActive else Icons.BookmarkInactive,
                        modifier = Modifier
                            .size(Theme.size.s24)
                            .clip(RoundedCornerShape(Theme.size.s12))
                            .clickable(onClick = onLike)
                            .background(Theme.color.neutral.n0)
                            .padding(Theme.size.s4),
                    ) { icon ->
                        Image(
                            imageVector = icon,
                            colorFilter = if (item.isSaved) null else ColorFilter.tint(Theme.color.neutral.n90),
                            contentDescription = ContentDescription.ICON,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }
        AsyncImage(
            model = item.recipe.image,
            contentDescription = ContentDescription.IMAGE,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(IMAGE_SIZE)
                .clip(CircleShape)
                .clickable(onClick = onClick)
                .background(Theme.color.neutral.n20, CircleShape),
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    PopularCategoryRecipeCard(
        item = RecipeUiModel.stub,
        onClick = {},
        onLike = {},
    )
}
