package idp.cookinator.feature.main.screen.home.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_trending_item_time
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.BookmarkActive
import idp.cookinator.coreui.vector.BookmarkInactive
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.More
import idp.cookinator.coreui.vector.Star
import idp.cookinator.model.Recipe
import org.jetbrains.compose.resources.stringResource

private const val ITEM_IMAGE_HEIGHT_RATIO = 280 / 180f

@Composable
internal fun HomeTrendingItem(
    item: Recipe,
    imageHeight: Dp,
    selected: Boolean = false,
) {
    val density = LocalDensity.current
    val shape = RoundedCornerShape(Theme.size.s10)
    var itemWidth by remember { mutableStateOf(Dp.Hairline) }

    Box(
        modifier = Modifier
            .clip(shape)
            .clickable { /*TODO go to detail*/ }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = item.image,
                    contentDescription = ContentDescription.IMAGE,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(imageHeight)
                        .aspectRatio(ITEM_IMAGE_HEIGHT_RATIO)
                        .onSizeChanged { itemWidth = with(density) { it.width.toDp() } }
                        .background(
                            Theme.color.neutral.n20,
                            shape,
                        )
                        .clip(shape),
                )
                Text(
                    text = stringResource(
                        Res.string.home_trending_item_time,
                        item.readyInMinutes.toString(),
                    ),
                    style = Theme.typography.regular.small,
                    color = Theme.color.neutral.n0,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(Theme.size.s8)
                        .background(
                            color = Theme.color.neutral.n90.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(Theme.size.s8),
                        ).padding(
                            vertical = Theme.size.s4,
                            horizontal = Theme.size.s8,
                        ),
                )
            }
            SpacerHeight(Theme.size.s12)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(itemWidth),
            ) {
                Text(
                    text = item.title,
                    style = Theme.typography.bold.p,
                    color = Theme.color.neutral.n90,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.More,
                    contentDescription = ContentDescription.ICON,
                    tint = Theme.color.neutral.n90,
                    modifier = Modifier
                        .size(Theme.size.s20)
                        .clickable { /*TODO show options*/ }
                )
            }
            SpacerHeight(Theme.size.s6)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s4),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(Theme.size.s8)
                .background(
                    color = Theme.color.neutral.n90.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(Theme.size.s8),
                ).padding(
                    start = Theme.size.s10,
                    top = Theme.size.s4,
                    end = Theme.size.s8,
                    bottom = Theme.size.s4,
                ),
        ) {
            Icon(
                imageVector = Icons.Star,
                contentDescription = ContentDescription.ICON,
                tint = Theme.color.neutral.n0,
                modifier = Modifier
                    .size(Theme.size.s16)
            )
            Text(
                text = item.servings.toString(),
                style = Theme.typography.bold.label,
                color = Theme.color.neutral.n0,
            )
        }
        Crossfade(
            targetState = if (selected) Icons.BookmarkActive else Icons.BookmarkInactive,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(Theme.size.s8)
                .size(Theme.size.s32)
                .clip(CircleShape)
                .background(
                    color = Theme.color.neutral.n0,
                    shape = CircleShape,
                )
                .clickable { /*TODO toggle saved*/ }
                .padding(Theme.size.s6)
        ) { icon ->
            Image(
                imageVector = icon,
                colorFilter = if (selected) null else ColorFilter.tint(Theme.color.neutral.n90),
                contentDescription = ContentDescription.ICON,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    HomeTrendingItem(
        item = Recipe.stub,
        imageHeight = 120.dp,
    )
}
