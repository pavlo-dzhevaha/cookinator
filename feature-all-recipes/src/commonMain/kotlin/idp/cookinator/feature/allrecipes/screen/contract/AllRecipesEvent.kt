package idp.cookinator.feature.allrecipes.screen.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface AllRecipesEvent : BaseEvent {
    data class NavigateToRecipe(val recipeId: Int) : AllRecipesEvent

    data object NavigateBack : AllRecipesEvent
}
