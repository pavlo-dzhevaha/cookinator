package idp.cookinator.feature.recipe.screen.detail.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_trending_item_time
import cookinator.localisation.generated.resources.recipe_detail_diet_dairy_free
import cookinator.localisation.generated.resources.recipe_detail_diet_gluten_free
import cookinator.localisation.generated.resources.recipe_detail_diet_vegan
import cookinator.localisation.generated.resources.recipe_detail_diet_vegetarian
import cookinator.localisation.generated.resources.recipe_detail_servings
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Clock
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Star
import idp.cookinator.model.Recipe
import org.jetbrains.compose.resources.stringResource

internal enum class RecipeDetailMetaLayout {
    Flow,
    Column,
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RecipeDetailMetaInfo(
    recipe: Recipe,
    layout: RecipeDetailMetaLayout,
    modifier: Modifier = Modifier,
) {
    val dietTags = remember(recipe) {
        buildList {
            if (recipe.vegetarian) add(Res.string.recipe_detail_diet_vegetarian)
            if (recipe.vegan) add(Res.string.recipe_detail_diet_vegan)
            if (recipe.glutenFree) add(Res.string.recipe_detail_diet_gluten_free)
            if (recipe.dairyFree) add(Res.string.recipe_detail_diet_dairy_free)
        }
    }

    val metaItems: @Composable () -> Unit = {
        recipe.readyInMinutes?.let { minutes ->
            MetaInfoRow(
                icon = Icons.Clock,
                text = stringResource(
                    Res.string.home_trending_item_time,
                    minutes.toString(),
                ),
            )
        }
        recipe.servings?.let { servings ->
            MetaInfoRow(
                icon = Icons.Star,
                text = stringResource(Res.string.recipe_detail_servings, servings),
            )
        }
        dietTags.forEach { tag ->
            DietTag(label = stringResource(tag))
        }
    }

    when (layout) {
        RecipeDetailMetaLayout.Flow -> {
            FlowRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
                verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
                content = { metaItems() },
            )
        }

        RecipeDetailMetaLayout.Column -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
                content = { metaItems() },
            )
        }
    }
}

@Composable
private fun MetaInfoRow(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Theme.size.s8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = ContentDescription.ICON,
            tint = Theme.color.neutral.n90,
            modifier = Modifier.size(Theme.size.s20),
        )
        Text(
            text = text,
            style = Theme.typography.regular.label,
            color = Theme.color.neutral.n90,
        )
    }
}

@Composable
private fun DietTag(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = label,
        style = Theme.typography.bold.label,
        color = Theme.color.neutral.n90,
        modifier = modifier
            .background(
                color = Theme.color.neutral.n10,
                shape = RoundedCornerShape(Theme.size.s8),
            )
            .padding(
                horizontal = Theme.size.s10,
                vertical = Theme.size.s4,
            ),
    )
}

@LightDarkPreview
@Composable
private fun PreviewFlow() = AppTheme {
    RecipeDetailMetaInfo(
        recipe = Recipe.stub.copy(
            vegetarian = true,
            glutenFree = true,
        ),
        layout = RecipeDetailMetaLayout.Flow,
        modifier = Modifier.padding(Theme.size.s20),
    )
}

@LightDarkPreview
@Composable
private fun PreviewColumn() = AppTheme {
    RecipeDetailMetaInfo(
        recipe = Recipe.stub.copy(
            vegetarian = true,
            glutenFree = true,
        ),
        layout = RecipeDetailMetaLayout.Column,
        modifier = Modifier.padding(Theme.size.s20),
    )
}
