package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_category_custom_hint
import cookinator.localisation.generated.resources.create_recipe_category_title
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Plus
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CreateRecipeCategorySection(
    availableCategories: List<String>,
    selectedCategories: Set<String>,
    customCategoryInput: String,
    onCategoryToggle: (String) -> Unit,
    onCustomInputChange: (String) -> Unit,
    onAddCustomCategory: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val allCategories = (availableCategories + selectedCategories.toList())
        .distinctBy { it.lowercase() }

    Column(
        verticalArrangement = Arrangement.spacedBy(Theme.size.s12),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.size.s20),
    ) {
        Text(
            text = stringResource(Res.string.create_recipe_category_title),
            style = Theme.typography.bold.h5,
            color = Theme.color.neutral.n100,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
            verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
        ) {
            allCategories.forEach { category ->
                CategoryChip(
                    label = category,
                    selected = category in selectedCategories,
                    onClick = { onCategoryToggle(category) },
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextField(
                value = customCategoryInput,
                onValueChange = onCustomInputChange,
                singleLine = true,
                textStyle = Theme.typography.regular.label.copy(color = Theme.color.neutral.n90),
                placeholder = {
                    Text(
                        text = stringResource(Res.string.create_recipe_category_custom_hint),
                        style = Theme.typography.regular.label,
                        color = Theme.color.neutral.n30,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Theme.color.neutral.n0,
                    unfocusedContainerColor = Theme.color.neutral.n0,
                    focusedIndicatorColor = Theme.color.system.transparent,
                    unfocusedIndicatorColor = Theme.color.system.transparent,
                    cursorColor = Theme.color.neutral.n90,
                ),
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = Theme.size.s1,
                        color = Theme.color.neutral.n20,
                        shape = RoundedCornerShape(Theme.size.s10),
                    ),
            )
            IconButton(onClick = onAddCustomCategory) {
                Icon(
                    imageVector = Icons.Plus,
                    contentDescription = ContentDescription.ICON,
                    tint = Theme.color.primary.p50,
                    modifier = Modifier.size(Theme.size.s24),
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) Theme.color.neutral.n0 else Theme.color.primary.p30,
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) Theme.color.primary.p50 else Theme.color.neutral.n0,
    )
    Text(
        text = label,
        style = Theme.typography.bold.small,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(Theme.size.s10))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = Theme.size.s12, vertical = Theme.size.s8),
    )
}
