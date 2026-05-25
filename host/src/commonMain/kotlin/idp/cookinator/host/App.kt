package idp.cookinator.host

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.feature.home.navigation.graph
import idp.cookinator.feature.navigation.features.NavigationHome
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.navigation.features.featureHomeSerializer
import idp.cookinator.feature.navigation.features.featureOnboardingSerializer
import idp.cookinator.feature.onboarding.navigation.graph
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.modules.SerializersModule
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    val storage = koinInject<AppStorage>()
    // TODO splash
    val onboardingCompleted = runBlocking { storage.isOnboardingCompleted() }
    // TODO useCase
    val styleRaw by storage.observeCurrentThemeStyle().collectAsStateWithLifecycle("")
    val style = ThemeStyle.fromStringOrDefault(styleRaw)
    AppTheme(
        style = style,
    ) {
        val backStack = rememberNavBackStack(
            configuration = SavedStateConfiguration {
                serializersModule = SerializersModule {
                    include(featureOnboardingSerializer)
                    include(featureHomeSerializer)
                }
            },
            when {
                onboardingCompleted -> NavigationHome.Home
                else -> NavigationOnboarding.Welcome
            },
        )

        NavDisplay(
            backStack = backStack,
        ) { key ->
            when (key) {
                is NavigationOnboarding -> key.graph(backStack)
                is NavigationHome -> key.graph(backStack)
                else -> NavEntry(key) { Text("Unknown destination: $key") }
            }
        }
    }
}
