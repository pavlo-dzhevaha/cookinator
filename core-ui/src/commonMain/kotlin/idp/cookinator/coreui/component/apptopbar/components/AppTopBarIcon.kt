package idp.cookinator.coreui.component.apptopbar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons

@Composable
internal fun AppTopBarIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit = {},
) {
    Icon(
        imageVector = icon,
        contentDescription = ContentDescription.ICON,
        tint = Theme.color.neutral.n90,
        modifier = modifier
            .size(Theme.size.s48)
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                role = Role.Button,
            )
            .padding(Theme.size.s12)
            .size(Theme.size.s24)
    )
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    AppTopBarIcon(
        icon = Icons.ArrowLeft,
    )
}
