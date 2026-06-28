package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

import idp.cookinator.feature.navigation.features.HomeRecipeSection

internal sealed interface HomeEvent : BaseEvent {
    data class NavigateToRecipe(val recipeId: Int) : HomeEvent

    data class NavigateToAllRecipes(val section: HomeRecipeSection) : HomeEvent
}