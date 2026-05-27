package idp.cookinator.feature.main.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.navigation.extension.Navigator
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
        HomeContent(
            bottomBarHeight = bottomBarHeight,
            state = state,
            onIntent = viewModel::onIntent,
        )
    }
}