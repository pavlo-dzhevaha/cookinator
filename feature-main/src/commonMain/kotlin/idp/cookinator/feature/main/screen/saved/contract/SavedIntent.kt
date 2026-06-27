package idp.cookinator.feature.main.screen.saved.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent
import idp.cookinator.feature.main.screen.home.model.RecipeUiModel

internal sealed interface SavedIntent : BaseIntent {
    data class OnToggleSaved(val model: RecipeUiModel) : SavedIntent

    data class OnRecipeClick(val model: RecipeUiModel) : SavedIntent

    data object OnRetry : SavedIntent
}
