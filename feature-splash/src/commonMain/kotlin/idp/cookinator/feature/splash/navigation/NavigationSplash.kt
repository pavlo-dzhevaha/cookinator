package idp.cookinator.feature.splash.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.viewmodel.base.MviWrapper
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.navigation.features.NavigationSplash
import idp.cookinator.feature.splash.screen.SplashContent
import idp.cookinator.feature.splash.screen.SplashScreenViewModel
import idp.cookinator.feature.splash.screen.contract.Event
import org.koin.compose.koinInject

fun NavigationSplash.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = NavEntry(this) {
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
