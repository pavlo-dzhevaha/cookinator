package idp.cookinator.coreui.component.button.primary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import idp.cookinator.coreui.component.button.core.ButtonType
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.ArrowRight
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.localisation.UiText
import idp.cookinator.localisation.UiText.Companion.asString
import idp.cookinator.localisation.UiText.Companion.asUiText

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: UiText,
    style: ButtonType = ButtonType.LARGE,
    hasIcon: Boolean = false,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = when {
            !enabled -> Theme.color.neutral.n20
            isPressed -> Theme.color.primary.p80
            else -> Theme.color.primary.p50
        },
    )

    val contentColor by animateColorAsState(
        targetValue = when {
            !enabled -> Theme.color.neutral.n50
            else -> Theme.color.system.white
        },
    )

    val containerPadding = when (style) {
        ButtonType.LARGE -> PaddingValues(
            vertical = Theme.size.s16,
            horizontal = Theme.size.s32,
        )

        ButtonType.SMALL -> PaddingValues(
            vertical = Theme.size.s8,
            horizontal = Theme.size.s16,
        )
    }

    val contentPadding = when (style) {
        ButtonType.LARGE -> Theme.size.s8
        ButtonType.SMALL -> Theme.size.s16
    }

    val textStyle = when (style) {
        ButtonType.LARGE -> Theme.typography.bold.p
        ButtonType.SMALL -> Theme.typography.bold.label
    }

    val loaderSize = when (style) {
        ButtonType.LARGE -> Theme.size.s20
        ButtonType.SMALL -> Theme.size.s16
    }
    val contentAlpha by animateFloatAsState(
        targetValue = if (loading) 0f else 1f,
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(Theme.size.s10),
            ).clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled && !loading,
                role = Role.Button,
                onClick = onClick,
            ).padding(containerPadding),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .alpha(contentAlpha),
        ) {
            Text(
                text = text.asString,
                style = textStyle,
                color = contentColor,
            )
            if (hasIcon) {
                Icon(
                    imageVector = Icons.ArrowRight,
                    contentDescription = ContentDescription.ICON,
                    tint = contentColor,
                    modifier = Modifier
                        .size(Theme.size.s20),
                )
            }
        }
        AnimatedVisibility(
            visible = loading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier
                    .size(loaderSize),
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun Preview() = AppTheme {
    Column(
        verticalArrangement = Arrangement.spacedBy(Theme.size.s40),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s40),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
            )
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                hasIcon = true,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s40),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                enabled = false,

                )
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                enabled = false,
                hasIcon = true,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s40),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                style = ButtonType.SMALL,
            )
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                hasIcon = true,
                style = ButtonType.SMALL,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s40),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                style = ButtonType.SMALL,
                enabled = false,
            )
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                hasIcon = true,
                style = ButtonType.SMALL,
                enabled = false,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s40),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                style = ButtonType.LARGE,
                loading = true,
            )
            PrimaryButton(
                text = "Placeholder".asUiText,
                onClick = {},
                hasIcon = true,
                style = ButtonType.SMALL,
                loading = true,
            )
        }
    }
}