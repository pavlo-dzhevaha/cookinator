package idp.cookinator.feature.main.screen.notification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.main.screen.notification.NotificationLayoutConfig
import idp.cookinator.feature.main.screen.notification.model.NotificationUiModel
import idp.cookinator.feature.main.screen.notification.util.formatRelativeTime

@Composable
internal fun NotificationListItem(
    item: NotificationUiModel,
    layout: NotificationLayoutConfig,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (item.isRead) {
        Theme.color.neutral.n0
    } else {
        Theme.color.primary.p100.copy(alpha = 0.35f)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.size.s12))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(Theme.size.s12),
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(layout.thumbnailSize)
                .clip(RoundedCornerShape(Theme.size.s8)),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = Theme.size.s12),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = item.title,
                    style = Theme.typography.bold.label,
                    color = Theme.color.neutral.n90,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                if (!item.isRead) {
                    Box(
                        modifier = Modifier
                            .padding(start = Theme.size.s8)
                            .size(Theme.size.s8)
                            .background(Theme.color.primary.p50, CircleShape),
                    )
                }
            }
            Text(
                text = item.body,
                style = Theme.typography.regular.p,
                color = Theme.color.neutral.n70,
                maxLines = layout.bodyMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = Theme.size.s4),
            )
            Text(
                text = formatRelativeTime(item.createdAt),
                style = Theme.typography.regular.small,
                color = Theme.color.neutral.n50,
                modifier = Modifier.padding(top = Theme.size.s4),
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    NotificationListItem(
        item = NotificationUiModel.stubs.first(),
        layout = NotificationLayoutConfig.portrait(),
        onClick = {},
    )
}
