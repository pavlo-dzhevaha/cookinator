package idp.cookinator.host

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.viewmodel.components.StateProvider
import idp.cookinator.feature.main.navigation.graph
import idp.cookinator.feature.navigation.extension.appConfiguration
import idp.cookinator.feature.navigation.extension.navigationPredictionTransitionSpec
import idp.cookinator.feature.navigation.extension.navigationTransitionSpec
import idp.cookinator.feature.navigation.features.NavigationMain
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.navigation.features.NavigationSettings
import idp.cookinator.feature.navigation.features.NavigationSplash
import idp.cookinator.feature.onboarding.navigation.graph
import idp.cookinator.feature.settings.navigation.graph
import idp.cookinator.feature.splash.navigation.graph
import org.koin.compose.viewmodel.koinViewModel

/**
 * The main entry point of the app. It sets up the navigation and theme for the entire application.
 */
@Composable
fun App() {
    val backStack = rememberNavBackStack(
        configuration = appConfiguration,
        NavigationSplash.Splash,
    )

    StateProvider(
        viewModel = koinViewModel<AppViewModel>(),
    ) { state ->
        AppTheme(
            locale = state.locale,
            style = state.style,
        ) {
            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
                transitionSpec = navigationTransitionSpec(true),
                popTransitionSpec = navigationTransitionSpec(false),
                predictivePopTransitionSpec = navigationPredictionTransitionSpec(),
            ) { key ->
                when (key) {
                    is NavigationSplash -> key.graph(backStack)
                    is NavigationOnboarding -> key.graph(backStack)
                    is NavigationMain -> key.graph(backStack)
                    is NavigationSettings -> key.graph(backStack)
                    else -> NavEntry(key) { Text("Unknown destination: $key") }
                }
            }
        }
    }
}
