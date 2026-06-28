package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.createrecipe.screen.create.contract.PickerType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRecipePickerSheet(
    pickerType: PickerType,
    title: String,
    options: List<Int>,
    selected: Int,
    formatOption: (Int) -> String,
    onDismiss: () -> Unit,
    onSelected: (Int) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column {
            Text(
                text = title,
                style = Theme.typography.bold.h5,
                color = Theme.color.neutral.n90,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.size.s20, vertical = Theme.size.s8),
            )
            LazyColumn(
                modifier = Modifier
                    .heightIn(max = 320.dp)
                    .padding(bottom = Theme.size.s24),
            ) {
                items(options, key = { it }) { option ->
                    val isSelected = option == selected
                    Text(
                        text = formatOption(option),
                        style = if (isSelected) Theme.typography.bold.p else Theme.typography.regular.p,
                        color = if (isSelected) Theme.color.primary.p50 else Theme.color.neutral.n90,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelected(option) }
                            .padding(horizontal = Theme.size.s20, vertical = Theme.size.s12),
                    )
                }
            }
        }
    }
}
