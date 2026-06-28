package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent
import idp.cookinator.feature.navigation.features.AllRecipesFilter
import idp.cookinator.coreui.model.RecipeUiModel

internal sealed interface HomeIntent : BaseIntent {
    data object OnFetchRecipe : HomeIntent

    data class OnToggleSaved(val model: RecipeUiModel) : HomeIntent

    data class OnRecipeClick(val model: RecipeUiModel) : HomeIntent

    data class OnSearchQueryChange(val query: String) : HomeIntent

    data class OnSeeAllClick(val filter: AllRecipesFilter) : HomeIntent

    data class OnCategorySelected(val category: String) : HomeIntent

    data class OnPopularCategorySeeAllClick(val category: String) : HomeIntent

    data class OnCustomizeRecipe(val model: RecipeUiModel) : HomeIntent
}
