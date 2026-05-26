package idp.cookinator.feature.splash.screen

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.viewmodel.base.MviWrapper
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.splash.screen.contract.Event
import org.koin.compose.koinInject

@Composable
internal fun SplashScreen(
    backStack: NavBackStack<NavKey>,
) {
    MviWrapper(
        viewModel = koinInject<SplashScreenViewModel>(),
        onEvent = { event ->
            when (event) {
                is Event.NavigateToStartDestination -> {
                    backStack.replace(event.startDestination)
                }
            }
        },
    ) {
        SplashContent()
    }
}
