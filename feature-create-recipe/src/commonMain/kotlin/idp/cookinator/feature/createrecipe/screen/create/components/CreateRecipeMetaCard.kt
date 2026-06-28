package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription

@Composable
internal fun CreateRecipeMetaCard(
    icon: ImageVector,
    label: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.size.s20)
            .clip(RoundedCornerShape(Theme.size.s12))
            .background(Theme.color.neutral.n10)
            .clickable(onClick = onClick)
            .padding(horizontal = Theme.size.s16, vertical = Theme.size.s12),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s12),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
        ) {
            BoxIconContainer(icon = icon)
            Text(
                text = label,
                style = Theme.typography.bold.p,
                color = Theme.color.neutral.n90,
            )
        }
        Text(
            text = value,
            style = Theme.typography.regular.label,
            color = Theme.color.neutral.n50,
        )
    }
}

@Composable
private fun BoxIconContainer(
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .size(Theme.size.s32)
            .background(Theme.color.neutral.n0, RoundedCornerShape(Theme.size.s10)),
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = ContentDescription.ICON,
            tint = Theme.color.primary.p50,
            modifier = Modifier.size(Theme.size.s20),
        )
    }
}
