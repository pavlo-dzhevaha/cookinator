package idp.cookinator.feature.onboarding.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.navigate
import idp.cookinator.feature.navigation.extension.navigateUp
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.feature.onboarding.screen.welcome.WelcomeScreen

fun NavigationOnboarding.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = when (this) {
    NavigationOnboarding.Welcome -> NavEntry(this) {
        WelcomeScreen(
            onStartTutorial = {
                backStack.navigate(NavigationOnboarding.Tutorial)
            },
        )
    }

    NavigationOnboarding.Tutorial -> NavEntry(this) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Welcome to Tutorial!")
                Button(
                    onClick = { backStack.navigateUp() },
                ) {
                    Text("Go Back if possible")
                }
            }
        }
    }
}
