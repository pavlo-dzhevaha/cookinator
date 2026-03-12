package idp.cookinator.host

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.feature.navigation.extension.navigate
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.navigation.features.featureOnboardingSerializer
import idp.cookinator.feature.onboarding.navigation.graph
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

// TODO remove
@Serializable
private data object Test : NavKey

// TODO remove
private val testSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        subclass(Test::class, Test.serializer())
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    // TODO setup dark mode toggle
    val style = ThemeStyle.LIGHT
    AppTheme(
        style = style,
    ) {
        // TODO read from ViewModel to determine start destination
        val startDestination = Test
        val backStack = rememberNavBackStack(
            configuration = SavedStateConfiguration {
                serializersModule = SerializersModule {
                    include(testSerializer) // TODO remove
                    include(featureOnboardingSerializer)
                }
            },
            startDestination,
        )

        NavDisplay(
            backStack = backStack,
        ) { key ->
            when (key) {
                // TODO remove
                Test -> NavEntry(key) {
                    TestUi(backStack)
                }

                is NavigationOnboarding -> key.graph(backStack)
                else -> NavEntry(key) { Text("Unknown destination: $key") }
            }
        }
    }
}

// TODO remove
@Composable
private fun TestUi(
    backStack: NavBackStack<NavKey>,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.neutral.white),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Hello, Cookinator! Running on ${getPlatform().name}."
            )
            Button(
                onClick = {
                    backStack.navigate(
                        key = NavigationOnboarding.Welcome,
                        clearBackStack = true,
                    )
                },
            ) {
                Text("Go to onboarding")
            }
        }
    }
}
