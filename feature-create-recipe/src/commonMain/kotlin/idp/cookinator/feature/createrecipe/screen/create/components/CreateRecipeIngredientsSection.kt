package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_ingredient_name_hint
import cookinator.localisation.generated.resources.create_recipe_ingredient_quantity_hint
import cookinator.localisation.generated.resources.create_recipe_ingredients_title
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.MinusBorder
import idp.cookinator.coreui.vector.PlusBorder
import idp.cookinator.feature.createrecipe.screen.create.contract.IngredientFormItem
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CreateRecipeIngredientsSection(
    ingredients: List<IngredientFormItem>,
    onNameChange: (String, String) -> Unit,
    onDescriptionChange: (String, String) -> Unit,
    onRemove: (String) -> Unit,
    onAddAfter: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Theme.size.s12),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.size.s20),
    ) {
        Text(
            text = stringResource(Res.string.create_recipe_ingredients_title),
            style = Theme.typography.bold.h5,
            color = Theme.color.neutral.n100,
        )
        ingredients.forEachIndexed { index, item ->
            val isLast = index == ingredients.lastIndex
            IngredientRow(
                item = item,
                showPlus = isLast,
                onNameChange = { onNameChange(item.localId, it) },
                onDescriptionChange = { onDescriptionChange(item.localId, it) },
                onAction = {
                    if (isLast) onAddAfter(item.localId) else onRemove(item.localId)
                },
            )
        }
    }
}

@Composable
private fun IngredientRow(
    item: IngredientFormItem,
    showPlus: Boolean,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAction: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        IngredientField(
            value = item.name,
            onValueChange = onNameChange,
            hint = stringResource(Res.string.create_recipe_ingredient_name_hint),
            modifier = Modifier.weight(1.4f),
        )
        IngredientField(
            value = item.description,
            onValueChange = onDescriptionChange,
            hint = stringResource(Res.string.create_recipe_ingredient_quantity_hint),
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = if (showPlus) Icons.PlusBorder else Icons.MinusBorder,
            contentDescription = ContentDescription.ICON,
            tint = Theme.color.neutral.n50,
            modifier = Modifier
                .size(Theme.size.s48)
                .clip(CircleShape)
                .clickable(
                    onClick = onAction,
                    role = Role.Button,
                )
                .padding(Theme.size.s12)
                .size(Theme.size.s24),
        )
    }
}

@Composable
private fun IngredientField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = Theme.typography.regular.label.copy(color = Theme.color.neutral.n90),
        placeholder = {
            Text(
                text = hint,
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
        shape = RoundedCornerShape(Theme.size.s10),
        modifier = modifier
            .border(
                width = Theme.size.s1,
                color = Theme.color.neutral.n20,
                shape = RoundedCornerShape(Theme.size.s10),
            ),
    )
}
