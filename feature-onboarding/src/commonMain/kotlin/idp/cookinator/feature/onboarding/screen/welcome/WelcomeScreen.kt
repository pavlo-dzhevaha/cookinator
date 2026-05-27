package idp.cookinator.feature.onboarding.screen.welcome

import androidx.compose.runtime.Composable
import idp.cookinator.coreui.viewmodel.base.MviStateProvider
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.navigation.features.NavigationMain
import idp.cookinator.feature.onboarding.screen.welcome.contract.Event
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun WelcomeScreen(
    navigator: Navigator,
    viewModel: WelcomeScreenViewModel = koinViewModel(),
) {
    MviStateProvider(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                Event.Continue -> navigator.replace(NavigationMain.Main)
            }
        },
    ) { state ->
        WelcomeContent(
            state = state,
            onIntent = viewModel::onIntent,
        )
    }
}
