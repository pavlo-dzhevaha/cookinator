package idp.cookinator.feature.recipe.screen.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.utils.isPortrait
import idp.cookinator.coreui.vector.BookmarkActive
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.model.Recipe

private const val HERO_IMAGE_ASPECT_RATIO = 335f / 200f

@Composable
internal fun RecipeDetailHeroSection(
    recipe: Recipe,
    isSaved: Boolean,
    modifier: Modifier = Modifier,
) {
    val portrait = isPortrait()

    val sectionModifier = modifier
        .fillMaxWidth()
        .padding(horizontal = Theme.size.s20)
        .padding(top = Theme.size.s12)

    if (portrait) {
        Column(modifier = sectionModifier) {
            RecipeDetailImage(
                imageUrl = recipe.image,
                isSaved = isSaved,
                modifier = Modifier.fillMaxWidth(),
            )
            SpacerHeight(Theme.size.s16)
            RecipeDetailMetaInfo(recipe = recipe)
        }
    } else {
        Row(
            modifier = sectionModifier,
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s16),
            verticalAlignment = Alignment.Top,
        ) {
            RecipeDetailImage(
                imageUrl = recipe.image,
                isSaved = isSaved,
                modifier = Modifier.weight(1f),
            )
            RecipeDetailMetaInfo(
                recipe = recipe,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun RecipeDetailImage(
    imageUrl: String?,
    isSaved: Boolean,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(Theme.size.s12)

    Box(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = ContentDescription.IMAGE,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(HERO_IMAGE_ASPECT_RATIO)
                .clip(shape)
                .background(Theme.color.neutral.n20, shape),
        )
        if (isSaved) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Theme.size.s8)
                    .size(Theme.size.s32)
                    .clip(CircleShape)
                    .background(
                        color = Theme.color.neutral.n0,
                        shape = CircleShape,
                    )
                    .padding(Theme.size.s6),
            ) {
                Image(
                    imageVector = Icons.BookmarkActive,
                    contentDescription = ContentDescription.ICON,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun PreviewStacked() = AppTheme {
    RecipeDetailHeroSection(
        recipe = Recipe.stub,
        isSaved = true,
    )
}
