package idp.cookinator.feature.main.screen.home.contract

import idp.cookinator.coreui.viewmodel.base.BaseState
import idp.cookinator.network.model.RandomRecipesResponse

internal data class HomeState(
    val isLoading: Boolean,
    val query: String,
    val result: Result<RandomRecipesResponse>?,
    val cachedResult: Result<RandomRecipesResponse>?,
) : BaseState {
    companion object {
        val initialState = HomeState(
            isLoading = false,
            query = "",
            result = null,
            cachedResult = null,
        )
    }
}
