package idp.cookinator.feature.main.screen.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme

@Composable
internal fun CategoryFilterChips(
    categories: List<String>,
    selected: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = Theme.size.s20),
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
        modifier = modifier,
    ) {
        items(
            items = categories,
            key = { it },
        ) { category ->
            val isSelected = category == selected
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Theme.color.neutral.n0 else Theme.color.primary.p30,
                label = "text_$category",
            )
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Theme.color.primary.p50 else Theme.color.neutral.n0,
                label = "background_$category",
            )
            Text(
                text = category,
                style = Theme.typography.bold.small,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(Theme.size.s10))
                    .background(backgroundColor)
                    .clickable { onSelect(category) }
                    .padding(
                        horizontal = Theme.size.s12,
                        vertical = Theme.size.s8,
                    ),
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    CategoryFilterChips(
        categories = listOf("Salad", "Breakfast", "Appetizer", "Noodle"),
        selected = "Breakfast",
        onSelect = {},
    )
}
