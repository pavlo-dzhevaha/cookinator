package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

import idp.cookinator.feature.navigation.features.AllRecipesFilter

internal sealed interface HomeEvent : BaseEvent {
    data class NavigateToRecipe(val recipeId: Int) : HomeEvent

    data class NavigateToAllRecipes(val filter: AllRecipesFilter) : HomeEvent

    data class NavigateToCustomizeRecipe(val recipeId: Int) : HomeEvent
}