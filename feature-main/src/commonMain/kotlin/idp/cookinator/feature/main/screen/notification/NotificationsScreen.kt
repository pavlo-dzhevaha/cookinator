package idp.cookinator.feature.main.screen.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.main.screen.notification.contract.NotificationsEvent
import idp.cookinator.feature.main.screen.notification.contract.NotificationsIntent
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigate
import idp.cookinator.feature.navigation.features.NavigationRecipe
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun NotificationsScreen(
    navigator: Navigator,
    viewModel: NotificationsViewModel = koinViewModel(),
    bottomBarHeight: Dp,
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                is NotificationsEvent.NavigateToRecipe -> {
                    navigator.navigate(NavigationRecipe.Detail(event.recipeId))
                }
            }
        },
    ) { state ->
        state.uiState.Render(
            loading = {
                CircularProgressIndicator()
            },
            empty = {
                NotificationsContent(
                    state = state,
                    onIntent = viewModel::onIntent,
                    bottomBarHeight = bottomBarHeight,
                )
            },
            error = {
                InfoContainer(
                    title = "An error occurred",
                    onAction = { viewModel.onIntent(NotificationsIntent.OnRetry) },
                )
            },
            success = {
                NotificationsContent(
                    state = state,
                    onIntent = viewModel::onIntent,
                    bottomBarHeight = bottomBarHeight,
                )
            },
            nonSuccessModifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
        )
    }
}
