package idp.cookinator.feature.main.screen.home

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.DomainManager
import idp.cookinator.feature.main.screen.home.contract.HomeEvent
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState
import idp.cookinator.model.Recipe

internal class HomeViewModel(
    private val domain: DomainManager,
) : MviViewModel<HomeState, HomeIntent, HomeEvent>(HomeState.initialState) {
    init {
        fetchData()
    }

    private val items = mutableListOf<Recipe>()

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnFetchRecipe -> fetchData()
            is HomeIntent.OnSearchQueryChange -> changeSearchQuery(intent.query)
        }
    }

    private fun changeSearchQuery(query: String) {
        updateState { it.copy(query = query) }
    }

    private fun fetchData() = launch {
        updateState {
            it.copy(
                uiState = UiState.LOADING,
            )
        }
        domain
            .getRandomRecipes()
            .onSuccess { list ->
                if (list.isEmpty()) {
                    updateState {
                        it.copy(
                            uiState = UiState.EMPTY,
                        )
                    }
                    return@launch
                }
                items.addAll(list)
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        trending = list.take(10),
                    )
                }
            }.onFailure {
                updateState {
                    it.copy(
                        uiState = UiState.ERROR,
                    )
                }
            }
    }
}
