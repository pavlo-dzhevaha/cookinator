package idp.cookinator.feature.allrecipes.screen.contract

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.viewmodel.base.BaseIntent

internal sealed interface AllRecipesIntent : BaseIntent {
    data class OnRecipeClick(val model: RecipeUiModel) : AllRecipesIntent

    data class OnToggleSaved(val model: RecipeUiModel) : AllRecipesIntent

    data object OnRetry : AllRecipesIntent

    data object OnBack : AllRecipesIntent

    data class OnCustomizeRecipe(val model: RecipeUiModel) : AllRecipesIntent
}
