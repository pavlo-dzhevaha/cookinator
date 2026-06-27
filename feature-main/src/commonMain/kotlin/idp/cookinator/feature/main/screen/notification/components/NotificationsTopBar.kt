package idp.cookinator.feature.main.screen.notification.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.notifications_clear_all
import cookinator.localisation.generated.resources.notifications_send_now
import cookinator.localisation.generated.resources.notifications_title
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.More
import org.jetbrains.compose.resources.stringResource

@Composable
private fun TopBarActionIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
            .size(Theme.size.s24),
    )
}

@Composable
internal fun NotificationsTopBar(
    isOptionsMenuExpanded: Boolean,
    onOptionsClick: () -> Unit,
    onDismissOptions: () -> Unit,
    onClearAll: () -> Unit,
    onSendNow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val optionsMenuWidth = 240.dp

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            SpacerHeight(height = Theme.size.s20)
            Text(
                text = stringResource(Res.string.notifications_title),
                style = Theme.typography.bold.h4,
                color = Theme.color.neutral.n90,
                modifier = Modifier.padding(
                    start = Theme.size.s20,
                    bottom = Theme.size.s20,
                ),
            )
        }
        Box {
            TopBarActionIcon(
                icon = Icons.More,
                onClick = onOptionsClick,
            )
            DropdownMenu(
                expanded = isOptionsMenuExpanded,
                onDismissRequest = onDismissOptions,
                offset = DpOffset(
                    x = Theme.size.s48 - optionsMenuWidth,
                    y = Theme.size.s4,
                ),
                modifier = Modifier.width(optionsMenuWidth),
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(Res.string.notifications_clear_all)) },
                    onClick = onClearAll,
                )
                DropdownMenuItem(
                    text = { Text(stringResource(Res.string.notifications_send_now)) },
                    onClick = onSendNow,
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    NotificationsTopBar(
        isOptionsMenuExpanded = true,
        onOptionsClick = {},
        onDismissOptions = {},
        onClearAll = {},
        onSendNow = {},
    )
}
