package idp.cookinator.feature.onboarding.screen.welcome

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.viewmodel.base.MviWrapper
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.navigation.features.NavigationHost
import idp.cookinator.feature.onboarding.screen.welcome.contract.Event
import org.koin.compose.koinInject

@Composable
internal fun WelcomeScreen(
    backStack: NavBackStack<NavKey>,
    viewModel: WelcomeScreenViewModel = koinInject(),
) {
    MviWrapper(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                Event.Continue -> backStack.replace(NavigationHost.Host)
            }
        },
    ) { state ->
        WelcomeContent(
            state = state,
            onIntent = viewModel::onIntent,
        )
    }
}
