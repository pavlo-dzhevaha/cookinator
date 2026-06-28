package idp.cookinator.feature.recipe.screen.detail.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_show_less
import cookinator.localisation.generated.resources.recipe_detail_show_more
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.HtmlText
import idp.cookinator.coreui.utils.defaultTween
import idp.cookinator.model.Recipe
import org.jetbrains.compose.resources.stringResource

private const val COLLAPSED_MAX_LINES = 4

@Composable
internal fun RecipeDetailDescription(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    val summary = recipe.summary?.trim().orEmpty()
    if (summary.isEmpty()) return

    var expanded by remember(summary) { mutableStateOf(false) }
    var hasOverflow by remember(summary) { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = defaultTween()),
    ) {
        HtmlText(
            html = summary,
            style = Theme.typography.regular.p,
            color = Theme.color.neutral.n90,
            modifier = Modifier.fillMaxWidth(),
            maxLines = if (expanded) Int.MAX_VALUE else COLLAPSED_MAX_LINES,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { result ->
                if (!expanded) {
                    hasOverflow = result.hasVisualOverflow
                }
            },
        )
        if (hasOverflow) {
            SpacerHeight(Theme.size.s8)
            Crossfade(
                targetState = expanded,
                animationSpec = defaultTween(),
            ) { isExpanded ->
                Text(
                    text = stringResource(
                        if (isExpanded) {
                            Res.string.recipe_detail_show_less
                        } else {
                            Res.string.recipe_detail_show_more
                        },
                    ),
                    style = Theme.typography.bold.label,
                    color = Theme.color.primary.p50,
                    modifier = Modifier.clickable { expanded = !expanded },
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    RecipeDetailDescription(
        recipe = Recipe.stub.copy(
            summary = "<p>A hearty salad with <b>fresh greens</b> and seasonal vegetables. " +
                "Perfect for a light lunch or dinner side. Toss with your favorite dressing " +
                "and serve immediately for the best texture and flavor.</p>",
        ),
        modifier = Modifier.padding(Theme.size.s20),
    )
}
