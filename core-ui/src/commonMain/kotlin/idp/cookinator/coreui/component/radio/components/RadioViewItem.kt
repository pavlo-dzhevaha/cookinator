package idp.cookinator.coreui.component.radio.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import idp.cookinator.coreui.component.spacer.SpacerWidth
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.isPortrait

@Composable
internal fun RadioViewItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val isPortrait = isPortrait()

    val animatedColor by animateColorAsState(
        targetValue = when {
            selected -> Theme.color.primary.p20
            else -> Theme.color.system.transparent
        },
    )

    val modifier = modifier
        .selectable(
            selected = selected,
            enabled = !selected,
            onClick = onClick,
            role = Role.RadioButton
        ).background(color = animatedColor)

    if (isPortrait) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
        ) {
            SpacerWidth(Theme.size.s12)
            RadioButton(
                selected = selected,
                onClick = null,
            )
            SpacerWidth(Theme.size.s12)
            Text(
                text = text,
                style = Theme.typography.regular.h4,
                color = Theme.color.neutral.n90,
                modifier = Modifier,
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier,
        ) {
            RadioButton(
                selected = selected,
                onClick = null,
            )
            Text(
                text = text,
                style = Theme.typography.regular.h4,
                color = Theme.color.neutral.n90,
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    RadioViewItem(
        text = "Option 1",
        selected = true,
        onClick = {},
    )
}

@LightDarkPreview
@Composable
private fun PreviewUnselected() = AppTheme {
    RadioViewItem(
        text = "Option 2",
        selected = false,
        onClick = {},
    )
}
