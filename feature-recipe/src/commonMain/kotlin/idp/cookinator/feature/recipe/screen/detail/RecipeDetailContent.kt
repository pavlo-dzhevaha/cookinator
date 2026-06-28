package idp.cookinator.feature.recipe.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_title
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.recipe.screen.detail.components.IngredientListItem
import idp.cookinator.feature.recipe.screen.detail.components.IngredientsSectionHeader
import idp.cookinator.feature.recipe.screen.detail.components.RecipeDetailHeroSection
import idp.cookinator.feature.recipe.screen.detail.components.RecipeDetailTopBar
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailIntent
import idp.cookinator.feature.recipe.screen.detail.contract.RecipeDetailState
import idp.cookinator.model.Recipe
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RecipeDetailContent(
    state: RecipeDetailState,
    onIntent: (RecipeDetailIntent) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val recipe = state.recipe ?: return
    val ingredients = recipe.extendedIngredients

    LazyColumn(
        contentPadding = PaddingValues(bottom = Theme.size.s16),
        modifier = modifier
            .fillMaxSize()
            .background(Theme.color.neutral.n0)
            .safeContentPadding(),
    ) {
        stickyHeader {
            RecipeDetailTopBar(
                title = stringResource(Res.string.recipe_detail_title, recipe.title),
                onBack = onBack,
                onToggleFavorite = { onIntent(RecipeDetailIntent.OnToggleFavorite) },
            )
        }
        item {
            RecipeDetailHeroSection(
                recipe = recipe,
                isSaved = state.isSaved,
            )
        }
        item {
            IngredientsSectionHeader(
                itemCount = ingredients.size,
                modifier = Modifier
                    .padding(horizontal = Theme.size.s20)
                    .padding(top = Theme.size.s24, bottom = Theme.size.s16),
            )
        }
        itemsIndexed(
            items = ingredients,
            key = { index, ingredient ->
                "${index}_${ingredient.id}_${ingredient.original.orEmpty()}_${ingredient.name.orEmpty()}"
            },
        ) { _, ingredient ->
            IngredientListItem(
                ingredient = ingredient,
                modifier = Modifier
                    .padding(horizontal = Theme.size.s20)
                    .padding(bottom = Theme.size.s12),
            )
        }
    }
}

@LightDarkPreview
@Composable
internal fun RecipeDetailContentPreview() {
    AppTheme {
        RecipeDetailContent(
            state = RecipeDetailState.initialState.copy(
                uiState = UiState.SUCCESS,
                recipe = Recipe.stub,
            ),
            onIntent = {},
            onBack = {},
        )
    }
}