package idp.cookinator.feature.recipe.screen.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_ingredients
import cookinator.localisation.generated.resources.recipe_detail_ingredients_count
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun IngredientsSectionHeader(
    itemCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(Res.string.recipe_detail_ingredients),
            style = Theme.typography.bold.h5,
            color = Theme.color.neutral.n90,
        )
        Text(
            text = stringResource(
                Res.string.recipe_detail_ingredients_count,
                itemCount,
            ),
            style = Theme.typography.regular.label,
            color = Theme.color.neutral.n40,
        )
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    IngredientsSectionHeader(itemCount = 5)
}
