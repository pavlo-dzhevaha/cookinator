package idp.cookinator.feature.main.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_search_hint
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.utils.RemoveFocusWhenKeyboardHiddenEffect
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Search
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeSearch(
    query: String,
    onValueChange: (String) -> Unit,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    RemoveFocusWhenKeyboardHiddenEffect()

    Box(
        modifier = Modifier
            .onSizeChanged { size = it }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Theme.color.neutral.n0,
                        Theme.color.system.transparent,
                    ),
                    end = Offset(
                        0f,
                        size.height.toFloat(),
                    ),
                )
            )
            .padding(
                vertical = Theme.size.s12,
                horizontal = Theme.size.s20,
            )
            .background(
                color = Theme.color.neutral.n0,
                shape = RoundedCornerShape(Theme.size.s16),
            )
            .border(
                width = Theme.size.s1,
                color = Theme.color.neutral.n20,
                shape = RoundedCornerShape(Theme.size.s16),
            )
    ) {
        TextField(
            value = query,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = Theme.typography.regular.label.copy(
                color = Theme.color.neutral.n90,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Search,
                    contentDescription = ContentDescription.ICON,
                    tint = Theme.color.neutral.n20,
                )
            },
            placeholder = {
                Text(
                    stringResource(Res.string.home_search_hint),
                    style = Theme.typography.regular.label,
                    color = Theme.color.neutral.n30,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Theme.color.system.transparent,
                unfocusedContainerColor = Theme.color.system.transparent,
                disabledContainerColor = Theme.color.system.transparent,
                focusedIndicatorColor = Theme.color.system.transparent,
                unfocusedIndicatorColor = Theme.color.system.transparent,
                disabledIndicatorColor = Theme.color.system.transparent,
                cursorColor = Theme.color.neutral.n90,
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    HomeSearch(
        query = "",
        onValueChange = {},
    )
}
