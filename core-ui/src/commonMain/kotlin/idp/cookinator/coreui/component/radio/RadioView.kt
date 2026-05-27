package idp.cookinator.coreui.component.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import idp.cookinator.coreui.utils.isPortrait
import idp.cookinator.localisation.UiText.Companion.asString

@Composable
fun <T : RadioViewElement> RadioView(
    modifier: Modifier = Modifier,
    title: String? = null,
    selected: T? = null,
    items: List<T>,
    onSelect: (T) -> Unit,
) {
    val isPortrait = isPortrait()

    val corner = Theme.size.s24
    val shape = remember(isPortrait) {
        if (isPortrait) {
            RoundedCornerShape(corner)
        } else {
            RoundedCornerShape(percent = 50)
        }
    }

    val modifier = modifier
        .selectableGroup()
        .padding(horizontal = Theme.size.s16)
        .background(
            color = Theme.color.neutral.n0,
            shape = shape,
        )
        .clip(shape)

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
        if (isPortrait) {
            Column(
                modifier = modifier
            ) {
                items.forEach { item ->
                    val isSelected = item == selected
                    RadioViewItem(
                        text = item.title.asString,
                        selected = isSelected,
                        onClick = { onSelect(item) },
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
        } else {
            // Landscape orientation: Place items in a Row
            Row(
                modifier = modifier
            ) {
                items.forEach { item ->
                    val isSelected = item == selected
                    RadioViewItem(
                        text = item.title.asString,
                        selected = isSelected,
                        onClick = { onSelect(item) },
                        modifier = Modifier
                            .weight(1f),
                    )
                }
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
