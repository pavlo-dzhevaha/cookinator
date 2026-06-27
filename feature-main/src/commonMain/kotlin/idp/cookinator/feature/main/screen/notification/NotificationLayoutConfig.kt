package idp.cookinator.feature.main.screen.notification

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal data class NotificationLayoutConfig(
    val thumbnailSize: Dp,
    val bodyMaxLines: Int,
    val contentMaxWidth: Dp?,
    val horizontalPadding: Dp,
    val verticalSpacing: Dp,
) {
    companion object {
        fun portrait() = NotificationLayoutConfig(
            thumbnailSize = 56.dp,
            bodyMaxLines = 2,
            contentMaxWidth = null,
            horizontalPadding = 16.dp,
            verticalSpacing = 12.dp,
        )

        fun landscape() = NotificationLayoutConfig(
            thumbnailSize = 48.dp,
            bodyMaxLines = 2,
            contentMaxWidth = null,
            horizontalPadding = 16.dp,
            verticalSpacing = 8.dp,
        )

        fun wideDesktop() = NotificationLayoutConfig(
            thumbnailSize = 64.dp,
            bodyMaxLines = 3,
            contentMaxWidth = 560.dp,
            horizontalPadding = 20.dp,
            verticalSpacing = 12.dp,
        )

        fun from(isPortrait: Boolean, longLandscape: Boolean): NotificationLayoutConfig = when {
            longLandscape -> wideDesktop()
            !isPortrait -> landscape()
            else -> portrait()
        }
    }
}
