package idp.cookinator.feature.main.screen.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.notifications_all_set
import cookinator.localisation.generated.resources.notifications_empty_title
import cookinator.localisation.generated.resources.notifications_send_now
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.model.UiState
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.isPortrait
import idp.cookinator.feature.main.screen.notification.components.NotificationFilterTabs
import idp.cookinator.feature.main.screen.notification.components.NotificationListItem
import idp.cookinator.feature.main.screen.notification.components.NotificationSectionHeader
import idp.cookinator.feature.main.screen.notification.components.NotificationsTopBar
import idp.cookinator.feature.main.screen.notification.contract.NotificationsIntent
import idp.cookinator.feature.main.screen.notification.contract.NotificationsState
import idp.cookinator.feature.main.screen.notification.model.NotificationFilter
import idp.cookinator.feature.main.screen.notification.model.NotificationUiModel
import idp.cookinator.feature.main.screen.notification.model.emptyMessage
import idp.cookinator.localisation.UiText.Companion.asUiText
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NotificationsContent(
    state: NotificationsState,
    onIntent: (NotificationsIntent) -> Unit,
    modifier: Modifier = Modifier,
    bottomBarHeight: Dp = Dp.Hairline,
) {
    val size = LocalWindowInfo.current.containerSize
    val portrait = isPortrait()
    val longLandscape = size.width > size.height * 1.5
    val layout = remember(portrait, longLandscape) {
        NotificationLayoutConfig.from(isPortrait = portrait, longLandscape = longLandscape)
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        NotificationsTopBar(
            isOptionsMenuExpanded = state.isOptionsMenuExpanded,
            onOptionsClick = { onIntent(NotificationsIntent.OnOptionsClick) },
            onDismissOptions = { onIntent(NotificationsIntent.OnDismissOptions) },
            onClearAll = { onIntent(NotificationsIntent.OnClearAll) },
            onSendNow = { onIntent(NotificationsIntent.OnSendNow) },
        )

        NotificationFilterTabs(
            selected = state.selectedFilter,
            onSelect = { onIntent(NotificationsIntent.OnFilterSelected(it)) },
            horizontalPadding = layout.horizontalPadding,
        )

        when {
            state.uiState == UiState.EMPTY -> {
                EmptyState(
                    title = stringResource(Res.string.notifications_empty_title),
                    layout = layout,
                    bottomBarHeight = bottomBarHeight,
                    onSendNow = { onIntent(NotificationsIntent.OnSendNow) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }

            !state.hasFilteredItems -> {
                EmptyState(
                    title = stringResource(state.selectedFilter.emptyMessage()),
                    layout = layout,
                    bottomBarHeight = bottomBarHeight,
                    showSendAction = false,
                    onSendNow = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = layout.horizontalPadding,
                        end = layout.horizontalPadding,
                        bottom = bottomBarHeight + Theme.size.s16,
                    ),
                    verticalArrangement = Arrangement.spacedBy(layout.verticalSpacing),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .then(
                            if (layout.contentMaxWidth != null) {
                                Modifier
                                    .widthIn(max = layout.contentMaxWidth)
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            } else {
                                Modifier
                            },
                        ),
                ) {
                    state.sections.forEach { section ->
                        item(key = "header-${section.dayStartEpochMs}") {
                            NotificationSectionHeader(dayStartEpochMs = section.dayStartEpochMs)
                        }
                        items(
                            items = section.items,
                            key = { it.id },
                        ) { item ->
                            NotificationListItem(
                                item = item,
                                layout = layout,
                                onClick = { onIntent(NotificationsIntent.OnNotificationClick(item)) },
                                modifier = Modifier.animateItem(),
                            )
                        }
                    }
                    item(key = "all-set-footer") {
                        Text(
                            text = stringResource(Res.string.notifications_all_set),
                            style = Theme.typography.regular.label,
                            color = Theme.color.neutral.n50,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = Theme.size.s24,
                                    bottom = Theme.size.s16,
                                ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyState(
    title: String,
    layout: NotificationLayoutConfig,
    bottomBarHeight: Dp,
    onSendNow: () -> Unit,
    modifier: Modifier = Modifier,
    showSendAction: Boolean = true,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(
            start = layout.horizontalPadding,
            end = layout.horizontalPadding,
            bottom = bottomBarHeight + Theme.size.s16,
        ),
    ) {
        if (showSendAction) {
            InfoContainer(
                title = title,
                actionText = Res.string.notifications_send_now.asUiText,
                onAction = onSendNow,
            )
        } else {
            Text(
                text = title,
                style = Theme.typography.regular.h2,
                textAlign = TextAlign.Center,
                color = Theme.color.neutral.n90,
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    NotificationsContent(
        state = NotificationsState.initialState.copy(
            uiState = UiState.SUCCESS,
            allItems = NotificationUiModel.stubs,
            sections = NotificationUiModel.previewSections,
        ),
        onIntent = {},
    )
}

@LightDarkPreview
@Composable
private fun PreviewUnreadFilter() = AppTheme {
    NotificationsContent(
        state = NotificationsState.initialState.copy(
            uiState = UiState.SUCCESS,
            allItems = NotificationUiModel.stubs,
            sections = emptyList(),
            selectedFilter = NotificationFilter.Unread,
        ),
        onIntent = {},
    )
}

@LightDarkPreview
@Composable
private fun PreviewReadFilter() = AppTheme {
    NotificationsContent(
        state = NotificationsState.initialState.copy(
            uiState = UiState.SUCCESS,
            allItems = NotificationUiModel.stubs,
            sections = emptyList(),
            selectedFilter = NotificationFilter.Read,
        ),
        onIntent = {},
    )
}

@LightDarkPreview
@Composable
private fun PreviewWide() = AppTheme {
    Box(Modifier.fillMaxSize()) {
        NotificationsContent(
            state = NotificationsState.initialState.copy(
                uiState = UiState.SUCCESS,
                allItems = NotificationUiModel.stubs,
                sections = NotificationUiModel.previewSections,
            ),
            onIntent = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
