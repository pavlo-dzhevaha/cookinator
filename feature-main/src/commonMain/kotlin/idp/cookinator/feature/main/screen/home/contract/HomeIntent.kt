package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent

internal sealed interface HomeIntent : BaseIntent {
    data object OnFetchRecipe : HomeIntent

    data object OnLoadRecipe : HomeIntent

    data class OnSearchQueryChange(val query: String) : HomeIntent
}
