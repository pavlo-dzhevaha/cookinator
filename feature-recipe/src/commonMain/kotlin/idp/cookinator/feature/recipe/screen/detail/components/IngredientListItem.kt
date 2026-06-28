package idp.cookinator.feature.recipe.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.model.Ingredient

private val ingredientCardHeight = 76.dp
private val ingredientIconSize = 52.dp

@Composable
internal fun IngredientListItem(
    ingredient: Ingredient,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(Theme.size.s12)
    val iconShape = RoundedCornerShape(Theme.size.s10)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(ingredientCardHeight)
            .clip(shape)
            .background(Theme.color.neutral.n10)
            .padding(horizontal = Theme.size.s16, vertical = Theme.size.s12),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s16),
    ) {
        AsyncImage(
            model = ingredient.imageUrl(),
            contentDescription = ContentDescription.IMAGE,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(ingredientIconSize)
                .clip(iconShape)
                .background(Theme.color.neutral.n0, iconShape),
        )
        Text(
            text = ingredient.name.orEmpty(),
            style = Theme.typography.bold.p,
            color = Theme.color.neutral.n90,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = ingredient.original.orEmpty(),
            style = Theme.typography.regular.label,
            color = Theme.color.neutral.n40,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxHeight(),
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    Box(modifier = Modifier.padding(Theme.size.s20)) {
        IngredientListItem(
            ingredient = Ingredient(
                id = 1,
                name = "Bread",
                original = "200g",
                image = null,
            ),
        )
    }
}
