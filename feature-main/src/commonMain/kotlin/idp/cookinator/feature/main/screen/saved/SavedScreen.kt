package idp.cookinator.feature.main.screen.saved

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.main.navigation.internal.NavigationMainInternal
import idp.cookinator.feature.main.screen.saved.contract.SavedIntent
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.pushToTop
import idp.cookinator.localisation.UiText.Companion.asUiText
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SavedScreen(
    internalNavigator: Navigator,
    viewModel: SavedViewModel = koinViewModel(),
    bottomBarHeight: Dp,
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { _ -> }
    ) { state ->
        state.uiState.Render(
            loading = {
                CircularProgressIndicator()
            },
            empty = {
                InfoContainer(
                    title = "No saved recipes",
                    actionText = "Explore recipes".asUiText,
                    onAction = {
                        internalNavigator.pushToTop(NavigationMainInternal.Home)
                    }
                )
            },
            error = {
                InfoContainer(
                    title = "An error occurred",
                    onAction = { viewModel.onIntent(SavedIntent.OnRetry) }
                )
            },
            success = {
                SavedContent(
                    state = state,
                    onIntent = viewModel::onIntent,
                    bottomBarHeight = bottomBarHeight,
                )
            },
            nonSuccessModifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        )
    }
}
