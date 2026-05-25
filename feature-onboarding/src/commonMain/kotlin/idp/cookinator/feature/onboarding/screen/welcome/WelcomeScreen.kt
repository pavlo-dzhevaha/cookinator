package idp.cookinator.feature.onboarding.screen.welcome

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.viewmodel.base.MviWrapper
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.navigation.features.NavigationHome
import idp.cookinator.feature.onboarding.screen.welcome.contract.WelcomeScreenEvent
import org.koin.compose.koinInject

@Composable
fun WelcomeScreen(
    backStack: NavBackStack<NavKey>,
    viewModel: WelcomeScreenViewModel = koinInject(),
) {
    MviWrapper(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                WelcomeScreenEvent.Continue -> backStack.replace(NavigationHome.Home)
            }
        },
    ) { state ->
        WelcomeContent(
            state = state,
            onIntent = viewModel::onIntent,
        )
    }
}
