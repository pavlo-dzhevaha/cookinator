package idp.cookinator.coreui.component.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import idp.cookinator.coreui.component.radio.components.RadioViewItem
import idp.cookinator.coreui.component.radio.model.RadioViewElement
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme

@Composable
fun <T : RadioViewElement> RadioView(
    modifier: Modifier = Modifier,
    title: String? = null,
    selected: T? = null,
    items: List<T>,
    onSelect: (T) -> Unit,
) {
    val shape = remember { RoundedCornerShape(percent = 50) }
    Column {
        title?.also {
            Text(
                text = it,
                style = Theme.typography.bold.h4,
                color = Theme.color.neutral.n90,
                modifier = Modifier
                    .padding(
                        vertical = Theme.size.s12,
                        horizontal = Theme.size.s16,
                    ),
            )
        }
        Row(
            modifier = modifier
                .selectableGroup()
                .padding(horizontal = Theme.size.s16)
                .background(
                    color = Theme.color.neutral.n0,
                    shape = shape,
                )
                .clip(shape),
        ) {
            items.forEach { item ->
                val isSelected = item == selected
                RadioViewItem(
                    text = item.name,
                    selected = isSelected,
                    onClick = { onSelect(item) },
                    modifier = Modifier
                        .weight(1f),
                )
            }
        }
    }
}


@LightDarkPreview
@Composable
internal fun Preview() = AppTheme {
    val items = listOf(
        RadioViewElement.create("First"),
        RadioViewElement.create("Second"),
        RadioViewElement.create("Third"),
    )
    var selected by remember { mutableStateOf(items.first()) }
    RadioView(
        title = "Hello world!",
        selected = selected,
        items = items,
        onSelect = { item -> selected = item },
    )
}
