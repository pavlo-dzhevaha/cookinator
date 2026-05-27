package idp.cookinator.feature.main.screen.home

import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.feature.main.screen.home.contract.HomeEvent
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState
import idp.cookinator.network.NetworkManager

internal class HomeViewModel(
    private val network: NetworkManager,
) : MviViewModel<HomeState, HomeIntent, HomeEvent>(HomeState.initialState) {
    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnFetchRecipe -> fetchRandomRecipes()
            is HomeIntent.OnSearchQueryChange -> changeSearchQuery(intent.query)
        }
    }

    private fun fetchRandomRecipes() = launch {
        updateState { it.copy(isLoading = true) }
        val result = network.getRandomRecipes()
        updateState { it.copy(isLoading = false, result = result) }
    }

    private fun changeSearchQuery(query: String) {
        updateState { it.copy(query = query) }
    }
}
