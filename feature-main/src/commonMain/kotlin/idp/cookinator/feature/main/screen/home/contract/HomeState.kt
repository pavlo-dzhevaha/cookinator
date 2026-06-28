package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseState
import idp.cookinator.coreui.model.RecipeUiModel

internal data class HomeState(
    val uiState: UiState,
    val query: String,
    val trending: List<RecipeUiModel>,
    val categories: List<String>,
    val selectedCategory: String?,
    val popularRecipes: List<RecipeUiModel>,
    val showPopularSeeAll: Boolean,
    val recentlyViewed: List<RecipeUiModel>,
) : BaseState {
    companion object {
        val initialState = HomeState(
            uiState = UiState.LOADING,
            query = "",
            trending = emptyList(),
            categories = emptyList(),
            selectedCategory = null,
            popularRecipes = emptyList(),
            showPopularSeeAll = false,
            recentlyViewed = emptyList(),
        )
    }
}
