package idp.cookinator.feature.main.screen.home

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.DomainManager
import idp.cookinator.feature.main.screen.home.contract.HomeEvent
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.main.screen.home.contract.HomeState

internal class HomeViewModel(
    private val domain: DomainManager,
) : MviViewModel<HomeState, HomeIntent, HomeEvent>(HomeState.initialState) {
    init {
        fetchData()
    }

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
                updateState {
                    it.copy(
                        uiState = UiState.SUCCESS,
                        items = list,
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
