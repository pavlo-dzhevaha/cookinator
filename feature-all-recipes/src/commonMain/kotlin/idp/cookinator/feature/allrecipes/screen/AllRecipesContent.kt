package idp.cookinator.feature.allrecipes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.component.grid.AdaptiveLazyVerticalGrid
import idp.cookinator.coreui.component.recipecard.RecipeCard
import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.allrecipes.model.titleRes
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesIntent
import idp.cookinator.feature.allrecipes.screen.contract.AllRecipesState
import idp.cookinator.feature.navigation.features.HomeRecipeSection
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AllRecipesContent(
    section: HomeRecipeSection,
    state: AllRecipesState,
    onIntent: (AllRecipesIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AppTopBar(
            title = stringResource(section.titleRes()),
            leadingIcon = Icons.ArrowLeft,
            onLeadingAction = { onIntent(AllRecipesIntent.OnBack) },
        )
        AdaptiveLazyVerticalGrid {
            items(
                items = state.items,
                key = { it.recipe.id },
            ) { item ->
                RecipeCard(
                    item = item,
                    imageHeight = Dp.Unspecified,
                    onClick = { onIntent(AllRecipesIntent.OnRecipeClick(item)) },
                    onLike = { onIntent(AllRecipesIntent.OnToggleSaved(item)) },
                    modifier = Modifier.animateItem(),
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    AllRecipesContent(
        section = HomeRecipeSection.Trending,
        state = AllRecipesState.initialState.copy(
            items = RecipeUiModel.stubs,
        ),
        onIntent = {},
    )
}
