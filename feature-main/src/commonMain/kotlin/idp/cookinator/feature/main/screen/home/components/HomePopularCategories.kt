package idp.cookinator.feature.main.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_popular_category_title
import idp.cookinator.coreui.component.recipecard.PopularCategoryRecipeCard
import idp.cookinator.coreui.component.recipecard.PopularCategorySeeAllCard
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomePopularCategories(
    categories: List<String>,
    selectedCategory: String?,
    items: List<RecipeUiModel>,
    showSeeAll: Boolean,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (categories.isEmpty()) return
    val listState = rememberLazyListState()

    LaunchedEffect(selectedCategory) {
        listState.animateScrollToItem(0)
    }

    Column(
        modifier = modifier.padding(vertical = Theme.size.s12),
    ) {
        Text(
            text = stringResource(Res.string.home_popular_category_title),
            style = Theme.typography.bold.h5,
            color = Theme.color.neutral.n90,
            modifier = Modifier.padding(horizontal = Theme.size.s20),
        )
        SpacerHeight(Theme.size.s16)
        CategoryFilterChips(
            categories = categories,
            selected = selectedCategory,
            onSelect = { onIntent(HomeIntent.OnCategorySelected(it)) },
        )
        SpacerHeight(Theme.size.s16)
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = Theme.size.s20),
            horizontalArrangement = Arrangement.spacedBy(Theme.size.s16),
        ) {
            items(
                items = items,
                key = { it.recipe.id },
            ) { item ->
                PopularCategoryRecipeCard(
                    item = item,
                    onClick = { onIntent(HomeIntent.OnRecipeClick(item)) },
                    onLike = { onIntent(HomeIntent.OnToggleSaved(item)) },
                )
            }
            if (showSeeAll && selectedCategory != null) {
                item(key = "see-all-$selectedCategory") {
                    PopularCategorySeeAllCard(
                        onClick = {
                            onIntent(HomeIntent.OnPopularCategorySeeAllClick(selectedCategory))
                        },
                    )
                }
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    HomePopularCategories(
        categories = listOf("salad", "breakfast", "noodle"),
        selectedCategory = "breakfast",
        items = RecipeUiModel.stubs.take(3),
        showSeeAll = true,
        onIntent = {},
    )
}
