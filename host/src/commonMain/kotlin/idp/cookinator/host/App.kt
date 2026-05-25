package idp.cookinator.host

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.navigation.features.featureHomeSerializer
import idp.cookinator.feature.navigation.features.featureOnboardingSerializer
import idp.cookinator.feature.onboarding.navigation.graph
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    // TODO setup dark mode toggle
    val style = ThemeStyle.LIGHT
    AppTheme(
        style = style,
    ) {
        // TODO read from ViewModel to determine start destination
        val startDestination = NavigationOnboarding.Welcome
        val backStack = rememberNavBackStack(
            configuration = SavedStateConfiguration {
                serializersModule = SerializersModule {
                    include(featureOnboardingSerializer)
                    include(featureHomeSerializer)
                }
            },
            startDestination,
        )

        NavDisplay(
            backStack = backStack,
        ) { key ->
            when (key) {
                is NavigationOnboarding -> key.graph(backStack)
                else -> NavEntry(key) { Text("Unknown destination: $key") }
            }
        }
    }
}
