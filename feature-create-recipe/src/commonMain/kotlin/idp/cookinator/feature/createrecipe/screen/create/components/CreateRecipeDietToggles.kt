package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_diet_dairy_free
import cookinator.localisation.generated.resources.recipe_detail_diet_gluten_free
import cookinator.localisation.generated.resources.recipe_detail_diet_vegan
import cookinator.localisation.generated.resources.recipe_detail_diet_vegetarian
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.createrecipe.screen.create.contract.DietType
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CreateRecipeDietToggles(
    vegetarian: Boolean,
    vegan: Boolean,
    glutenFree: Boolean,
    dairyFree: Boolean,
    onToggle: (DietType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
        verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.size.s20),
    ) {
        DietChip(Res.string.recipe_detail_diet_vegetarian, vegetarian) { onToggle(DietType.Vegetarian) }
        DietChip(Res.string.recipe_detail_diet_vegan, vegan) { onToggle(DietType.Vegan) }
        DietChip(Res.string.recipe_detail_diet_gluten_free, glutenFree) { onToggle(DietType.GlutenFree) }
        DietChip(Res.string.recipe_detail_diet_dairy_free, dairyFree) { onToggle(DietType.DairyFree) }
    }
}

@Composable
private fun DietChip(
    labelRes: StringResource,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) Theme.color.neutral.n0 else Theme.color.neutral.n50,
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) Theme.color.primary.p50 else Theme.color.neutral.n10,
    )
    Text(
        text = stringResource(labelRes),
        style = Theme.typography.bold.small,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(Theme.size.s10))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = Theme.size.s12, vertical = Theme.size.s8),
    )
}
