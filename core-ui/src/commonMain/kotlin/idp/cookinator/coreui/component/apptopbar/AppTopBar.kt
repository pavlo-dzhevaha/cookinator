package idp.cookinator.coreui.component.apptopbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import idp.cookinator.coreui.component.apptopbar.components.AppTopBarIcon
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.More

/**
 * A top bar component that can be used across the app. It supports a title, an optional leading
 * icon, and an optional trailing icon. The icons can have their own click actions.
 *
 * @param modifier Modifier to be applied to the top bar.
 * @param title The title text to be displayed in the top bar.
 * @param leadingIcon An optional icon to be displayed on the left side of the top bar.
 * @param trailingIcon An optional icon to be displayed on the right side of the top bar.
 * @param onLeadingAction A lambda function that will be called when the leading icon is clicked.
 * @param onTrailingAction A lambda function that will be called when the trailing icon is clicked.
 */
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onLeadingAction: () -> Unit = {},
    onTrailingAction: () -> Unit = {},
) {
    val hasLeadingIcon = leadingIcon != null
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            leadingIcon?.also { icon ->
                AppTopBarIcon(
                    icon = icon,
                    onClick = onLeadingAction,
                    modifier = Modifier
                        .padding(bottom = Theme.size.s4),
                )
            } ?: SpacerHeight(height = Theme.size.s20)
            Text(
                text = title,
                style = Theme.typography.bold.h4,
                color = Theme.color.neutral.n90,
                modifier = Modifier
                    .padding(
                        start = Theme.size.s20,
                        bottom = if (!hasLeadingIcon) Theme.size.s20 else Theme.size.s12,
                    ),
            )
        }
        trailingIcon?.also { icon ->
            AppTopBarIcon(
                icon = icon,
                onClick = onTrailingAction,
                modifier = Modifier
                    .align(if (hasLeadingIcon) Alignment.Top else Alignment.CenterVertically)
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    AppTopBar(
        title = "Title",
    )
}

@LightDarkPreview
@Composable
private fun PreviewTitleLeading() = AppTheme {
    AppTopBar(
        title = "Title",
        leadingIcon = Icons.ArrowLeft,
    )
}

@LightDarkPreview
@Composable
private fun PreviewTitleTrailing() = AppTheme {
    AppTopBar(
        title = "Title",
        trailingIcon = Icons.More,
    )
}

@LightDarkPreview
@Composable
private fun PreviewTitleLeadingTrailing() = AppTheme {
    AppTopBar(
        title = "Title",
        leadingIcon = Icons.ArrowLeft,
        trailingIcon = Icons.More,
    )
}

@LightDarkPreview
@Composable
private fun PreviewDark() = AppTheme {
    AppTopBar(
        title = "Title",
        leadingIcon = Icons.ArrowLeft,
        trailingIcon = Icons.More,
    )
}
