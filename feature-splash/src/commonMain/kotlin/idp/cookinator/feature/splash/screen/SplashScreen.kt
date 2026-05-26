package idp.cookinator.feature.splash.screen

import androidx.compose.runtime.Composable
import idp.cookinator.coreui.viewmodel.base.MviWrapper
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.splash.screen.contract.Event
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SplashScreen(
    navigator: Navigator,
    viewModel: SplashScreenViewModel = koinViewModel(),
) {
    MviWrapper(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                is Event.NavigateToStartDestination -> {
                    navigator.replace(event.startDestination)
                }
            }
        },
    ) {
        SplashContent()
    }
}
