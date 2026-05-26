package idp.cookinator.host

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.feature.home.navigation.graph
import idp.cookinator.feature.navigation.features.NavigationHost
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.navigation.features.NavigationSplash
import idp.cookinator.feature.navigation.features.featureHomeSerializer
import idp.cookinator.feature.navigation.features.featureOnboardingSerializer
import idp.cookinator.feature.navigation.features.featureSplashSerializer
import idp.cookinator.feature.onboarding.navigation.graph
import idp.cookinator.feature.splash.navigation.graph
import idp.cookinator.preferences.AppStorage
import kotlinx.serialization.modules.SerializersModule
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    val storage = koinInject<AppStorage>()
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                include(featureSplashSerializer)
                include(featureOnboardingSerializer)
                include(featureHomeSerializer)
            }
        },
        NavigationSplash,
    )

    // TODO useCase
    val styleRaw by storage.observeCurrentThemeStyle().collectAsStateWithLifecycle("")
    val style = remember(styleRaw) { ThemeStyle.fromStringOrDefault(styleRaw) }

    AppTheme(
        style = style,
    ) {
        NavDisplay(
            backStack = backStack,
            transitionSpec = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                ) togetherWith slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                )
            },
            popTransitionSpec = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                ) togetherWith slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            },
            predictivePopTransitionSpec = { _ ->
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(300)
                ) + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                ) togetherWith scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(300)
                ) + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            }
        ) { key ->
            when (key) {
                is NavigationSplash -> key.graph(backStack)
                is NavigationOnboarding -> key.graph(backStack)
                is NavigationHost -> key.graph(backStack)
                else -> NavEntry(key) { Text("Unknown destination: $key") }
            }
        }
    }
}
