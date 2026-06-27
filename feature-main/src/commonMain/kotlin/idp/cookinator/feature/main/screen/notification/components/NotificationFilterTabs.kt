package idp.cookinator.feature.main.screen.notification.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.notifications_filter_all
import cookinator.localisation.generated.resources.notifications_filter_read
import cookinator.localisation.generated.resources.notifications_filter_unread
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.main.screen.notification.model.NotificationFilter
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NotificationFilterTabs(
    selected: NotificationFilter,
    onSelect: (NotificationFilter) -> Unit,
    horizontalPadding: Dp,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
        modifier = modifier
            .padding(
                start = horizontalPadding,
                end = horizontalPadding,
                bottom = Theme.size.s16,
            ),
    ) {
        NotificationFilter.entries.forEach { filter ->
            val isSelected = filter == selected
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Theme.color.neutral.n0 else Theme.color.primary.p30,
                label = "text_$filter"
            )
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) Theme.color.primary.p50 else Theme.color.system.transparent,
                label = "background_$filter"
            )
            Text(
                text = filter.label(),
                style = Theme.typography.bold.small,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(Theme.size.s10))
                    .background(backgroundColor)
                    .clickable { onSelect(filter) }
                    .padding(
                        horizontal = Theme.size.s12,
                        vertical = Theme.size.s8,
                    )
                    .weight(1f),
            )
        }
    }
}

@Composable
private fun NotificationFilter.label(): String = when (this) {
    NotificationFilter.All -> stringResource(Res.string.notifications_filter_all)
    NotificationFilter.Unread -> stringResource(Res.string.notifications_filter_unread)
    NotificationFilter.Read -> stringResource(Res.string.notifications_filter_read)
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    NotificationFilterTabs(
        selected = NotificationFilter.All,
        onSelect = {},
        horizontalPadding = Theme.size.s16,
    )
}
