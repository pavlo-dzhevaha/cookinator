package idp.cookinator.coreui.component.recipecard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_popular_category_see_all
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.ArrowRight
import idp.cookinator.coreui.vector.Icons
import org.jetbrains.compose.resources.stringResource

private val CARD_WIDTH = 150.dp
private val CARD_TOTAL_HEIGHT = 231.dp

@Composable
fun PopularCategorySeeAllCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(Theme.size.s12)

    Box(
        modifier = modifier
            .width(CARD_WIDTH)
            .height(CARD_TOTAL_HEIGHT)
            .clip(shape)
            .clickable(onClick = onClick)
            .background(Theme.color.neutral.n10, shape),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
            modifier = Modifier.padding(horizontal = Theme.size.s12),
        ) {
            Text(
                text = stringResource(Res.string.home_popular_category_see_all),
                style = Theme.typography.bold.label,
                color = Theme.color.primary.p50,
                textAlign = TextAlign.Center,
            )
            Icon(
                imageVector = Icons.ArrowRight,
                contentDescription = ContentDescription.ICON,
                tint = Theme.color.primary.p50,
                modifier = Modifier.size(Theme.size.s20),
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    PopularCategorySeeAllCard(onClick = {})
}
