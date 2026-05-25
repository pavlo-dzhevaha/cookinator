package idp.cookinator.feature.onboarding.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.onboarding.screen.welcome.WelcomeScreen
import idp.cookinator.feature.onboarding.screen.welcome.WelcomeScreenViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

fun NavigationOnboarding.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = when (this) {
    NavigationOnboarding.Welcome -> NavEntry(this) {
        val viewModel: WelcomeScreenViewModel = koinInject<WelcomeScreenViewModel>()
        val state by viewModel.collectState()
        val scope = rememberCoroutineScope()
        WelcomeScreen(
            state = state,
            onContinue = {
                scope.launch {
                    viewModel.onContinue()
                }
//                backStack.replace(NavigationHome.Home)
            },
        )
    }
}
