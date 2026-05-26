package idp.cookinator.coreui.component.radio.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme

@Composable
internal fun RadioViewItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val animatedColor by animateColorAsState(
        targetValue = when {
            selected -> Theme.color.primary.p20
            else -> Theme.color.system.transparent
        },
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .selectable(
                selected = selected,
                enabled = !selected,
                onClick = onClick,
                role = Role.RadioButton
            ).background(color = animatedColor),
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Text(
            text = text,
            style = Theme.typography.regular.h4,
        )
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
