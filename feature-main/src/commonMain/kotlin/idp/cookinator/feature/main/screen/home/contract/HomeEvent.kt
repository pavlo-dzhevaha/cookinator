package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface HomeEvent : BaseEvent {
    data class NavigateToRecipe(val recipeId: Int) : HomeEvent
}