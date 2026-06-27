package idp.cookinator.feature.main.screen.notification

import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.domain.DomainManager
import idp.cookinator.feature.main.screen.notification.contract.NotificationsEvent
import idp.cookinator.feature.main.screen.notification.contract.NotificationsIntent
import idp.cookinator.feature.main.screen.notification.contract.NotificationsState
import idp.cookinator.feature.main.screen.notification.model.NotificationFilter
import idp.cookinator.feature.main.screen.notification.model.NotificationUiModel
import idp.cookinator.feature.main.screen.notification.util.groupNotificationsByDay
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

internal class NotificationsViewModel(
    private val domain: DomainManager,
) : MviViewModel<NotificationsState, NotificationsIntent, NotificationsEvent>(
    NotificationsState.initialState,
) {
    private var observeJob: Job? = null

    init {
        observeNotifications()
    }

    override fun onIntent(intent: NotificationsIntent) {
        when (intent) {
            is NotificationsIntent.OnNotificationClick -> onNotificationClick(intent.item)
            is NotificationsIntent.OnFilterSelected -> onFilterSelected(intent.filter)
            NotificationsIntent.OnOptionsClick -> updateState { it.copy(isOptionsMenuExpanded = true) }
            NotificationsIntent.OnDismissOptions -> updateState { it.copy(isOptionsMenuExpanded = false) }
            NotificationsIntent.OnClearAll -> onClearAll()
            NotificationsIntent.OnSendNow -> onSendNow()
            NotificationsIntent.OnRetry -> observeNotifications()
        }
    }

    private fun onFilterSelected(filter: NotificationFilter) {
        updateState { current ->
            current.copy(
                selectedFilter = filter,
                sections = filterItems(current.allItems, filter),
            )
        }
    }

    private fun onNotificationClick(item: NotificationUiModel) = launch {
        if (!item.isRead) {
            domain.markNotificationRead(item.id)
        }
        sendEvent(NotificationsEvent.NavigateToRecipe(item.recipeId))
    }

    private fun onClearAll() = launch {
        updateState { it.copy(isOptionsMenuExpanded = false) }
        domain.clearNotifications()
    }

    private fun onSendNow() = launch {
        updateState { it.copy(isOptionsMenuExpanded = false) }
        domain.sendRecipeReminderNow()
    }

    private fun observeNotifications() {
        observeJob?.cancel()
        observeJob = launch {
            domain.notifications
                .onStart { updateState { it.copy(uiState = UiState.LOADING) } }
                .catch { updateState { it.copy(uiState = UiState.ERROR) } }
                .collectLatest { list ->
                    val items = list.map(NotificationUiModel::from)
                    updateState { current ->
                        val sections = filterItems(items, current.selectedFilter)
                        current.copy(
                            uiState = if (items.isEmpty()) UiState.EMPTY else UiState.SUCCESS,
                            allItems = items,
                            sections = sections,
                        )
                    }
                }
        }
    }

    private fun filterItems(
        items: List<NotificationUiModel>,
        filter: NotificationFilter,
    ) = groupNotificationsByDay(
        when (filter) {
            NotificationFilter.All -> items
            NotificationFilter.Unread -> items.filter { !it.isRead }
            NotificationFilter.Read -> items.filter { it.isRead }
        },
    )
}
