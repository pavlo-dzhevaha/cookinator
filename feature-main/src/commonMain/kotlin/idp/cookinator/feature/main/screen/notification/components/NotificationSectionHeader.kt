package idp.cookinator.feature.main.screen.notification.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.main.screen.notification.util.notificationSectionTitle

@Composable
internal fun NotificationSectionHeader(
    dayStartEpochMs: Long,
    modifier: Modifier = Modifier,
) {
    Text(
        text = notificationSectionTitle(dayStartEpochMs),
        style = Theme.typography.bold.label,
        color = Theme.color.neutral.n70,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = Theme.size.s8),
    )
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    NotificationSectionHeader(dayStartEpochMs = System.currentTimeMillis())
}
