package idp.cookinator.feature.main.screen.profile.contract

import idp.cookinator.coreui.model.RecipeUiModel
import idp.cookinator.coreui.viewmodel.base.BaseIntent

internal sealed interface ProfileIntent : BaseIntent {
    data class OnRecipeClick(val item: ProfileRecipeItem) : ProfileIntent
    data class OnDeleteRecipe(val item: ProfileRecipeItem) : ProfileIntent
    data object OnConfirmDeleteRecipe : ProfileIntent
    data object OnDismissDeleteDialog : ProfileIntent
    data class OnEditRecipe(val item: ProfileRecipeItem) : ProfileIntent
    data object OnRetry : ProfileIntent
}
