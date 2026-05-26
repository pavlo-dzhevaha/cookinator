package idp.cookinator.feature.home.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Search

@Composable
internal fun HomeContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues.Zero,
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
            .fillMaxSize(),
    ) {
        item {
            Text(
                "Find best recipes for cooking",
                style = Theme.typography.bold.h4,
                color = Theme.color.neutral.n90,
                modifier = Modifier
                    .padding(
                        vertical = Theme.size.s20,
                        horizontal = Theme.size.s20,
                    ),
            )
        }
        stickyHeader {
            var text by remember { mutableStateOf("") }
            var size by remember { mutableStateOf(IntSize.Zero) }
            Box(
                modifier = Modifier
                    .onSizeChanged { size = it }
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Theme.color.system.white,
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
                        color = Theme.color.system.white,
                        shape = RoundedCornerShape(Theme.size.s16),
                    )
                    .border(
                        width = Theme.size.s1,
                        color = Theme.color.neutral.n20,
                        shape = RoundedCornerShape(Theme.size.s16),
                    )
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
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
                            "Search recipes",
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
        items(15) { index ->
            Box(
                modifier = Modifier
                    .padding(Theme.size.s24)
            ) {
                Text("Home Item #$index")
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun Preview() = AppTheme {
    HomeContent()
}
