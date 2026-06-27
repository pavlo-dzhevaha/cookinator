package idp.cookinator.feature.main.screen.notification.contract

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.base.BaseState
import idp.cookinator.feature.main.screen.notification.model.NotificationFilter
import idp.cookinator.feature.main.screen.notification.model.NotificationSection
import idp.cookinator.feature.main.screen.notification.model.NotificationUiModel

internal data class NotificationsState(
    val uiState: UiState,
    val allItems: List<NotificationUiModel>,
    val sections: List<NotificationSection>,
    val selectedFilter: NotificationFilter,
    val isOptionsMenuExpanded: Boolean,
) : BaseState {
    val hasFilteredItems: Boolean
        get() = sections.any { it.items.isNotEmpty() }

    companion object {
        val initialState = NotificationsState(
            uiState = UiState.LOADING,
            allItems = emptyList(),
            sections = emptyList(),
            selectedFilter = NotificationFilter.All,
            isOptionsMenuExpanded = false,
        )
    }
}
