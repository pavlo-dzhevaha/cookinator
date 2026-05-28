package idp.cookinator.feature.main.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_empty_info
import cookinator.localisation.generated.resources.home_error_info
import idp.cookinator.coreui.component.info.InfoContainer
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.main.screen.home.contract.HomeIntent
import idp.cookinator.feature.navigation.extension.Navigator
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    navigator: Navigator,
    bottomBarHeight: Dp = Dp.Hairline,
    viewModel: HomeViewModel = koinViewModel(),
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { _ -> },
    ) { state ->
        state.uiState.Render(
            loading = {
                CircularProgressIndicator()
            },
            empty = {
                InfoContainer(
                    title = stringResource(Res.string.home_empty_info),
                    onRetry = { viewModel.onIntent(HomeIntent.OnFetchRecipe) },
                )
            },
            error = {
                InfoContainer(
                    title = stringResource(Res.string.home_error_info),
                    onRetry = { viewModel.onIntent(HomeIntent.OnFetchRecipe) },
                )
            },
            success = {
                HomeContent(
                    bottomBarHeight = bottomBarHeight,
                    state = state,
                    onIntent = viewModel::onIntent,
                )
            },
            nonSuccessModifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
        )
    }
}