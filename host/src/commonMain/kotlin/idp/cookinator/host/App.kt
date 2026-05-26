package idp.cookinator.host

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.utils.defaultTween
import idp.cookinator.feature.main.navigation.graph
import idp.cookinator.feature.navigation.extension.configuration
import idp.cookinator.feature.navigation.features.NavigationMain
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.navigation.features.NavigationSettings
import idp.cookinator.feature.navigation.features.NavigationSplash
import idp.cookinator.feature.onboarding.navigation.graph
import idp.cookinator.feature.settings.navigation.graph
import idp.cookinator.feature.splash.navigation.graph
import idp.cookinator.host.extension.observeThemeStyle

/**
 * The main entry point of the app. It sets up the navigation and theme for the entire application.
 */
@Composable
fun App() {
    val backStack = rememberNavBackStack(
        configuration = configuration,
        NavigationSplash.Splash,
    )

    val style by observeThemeStyle()

    AppTheme(
        style = style,
    ) {
        NavDisplay(
            backStack = backStack,
            transitionSpec = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = defaultTween()
                ) togetherWith slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = defaultTween()
                )
            },
            popTransitionSpec = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = defaultTween()
                ) togetherWith slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = defaultTween()
                )
            },
            predictivePopTransitionSpec = { _ ->
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = defaultTween()
                ) + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = defaultTween()
                ) togetherWith scaleOut(
                    targetScale = 0.9f,
                    animationSpec = defaultTween()
                ) + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = defaultTween()
                )
            }
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
