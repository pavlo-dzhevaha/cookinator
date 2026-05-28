package idp.cookinator.coreui.component.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.common_try_again
import idp.cookinator.coreui.component.button.primary.PrimaryButton
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.localisation.UiText
import idp.cookinator.localisation.UiText.Companion.asUiText

@Composable
fun InfoContainer(
    modifier: Modifier = Modifier,
    title: String,
    actionText: UiText = Res.string.common_try_again.asUiText,
    onAction: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Theme.size.s16),
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = Theme.typography.regular.h2,
            textAlign = TextAlign.Center,
            color = Theme.color.neutral.n90,
        )
        PrimaryButton(
            text = actionText,
            onClick = onAction,
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    InfoContainer(
        title = "This is an info container",
        onAction = {},
    )
}
