package idp.cookinator.feature.recipe.screen.detail.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent

internal sealed interface RecipeDetailIntent : BaseIntent {
    data object OnRetry : RecipeDetailIntent
    data object OnToggleFavorite : RecipeDetailIntent
    data object OnEdit : RecipeDetailIntent
}
