package idp.cookinator.feature.home.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.coreui.component.button.core.ButtonType
import idp.cookinator.coreui.component.button.primary.PrimaryButton
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.home.navigation.internal.NavigationHostInternal
import idp.cookinator.feature.home.navigation.internal.graph
import idp.cookinator.feature.home.navigation.internal.internalHostSerializer
import idp.cookinator.feature.home.screen.components.bottombar.BottomBar
import idp.cookinator.feature.home.screen.components.bottombar.model.BottomBarElement
import idp.cookinator.feature.navigation.extension.navigate
import idp.cookinator.feature.navigation.extension.replace
import idp.cookinator.feature.navigation.features.NavigationHost
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.localisation.UiText.Companion.asUiText
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.launch
import kotlinx.serialization.modules.SerializersModule
import org.koin.compose.koinInject

fun NavigationHost.graph(
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> = when (this) {
    NavigationHost.Host -> NavEntry(this) {
        val internalBackStack = rememberNavBackStack(
            configuration = SavedStateConfiguration {
                serializersModule = SerializersModule {
                    include(internalHostSerializer)
                }
            },
            NavigationHostInternal.Home,
        )
        var currentTab by remember { mutableStateOf(BottomBarElement.Home) }
        var isForward by remember { mutableStateOf(true) }
        //TODO Remove
        val scope = rememberCoroutineScope()
        val appStorage = koinInject<AppStorage>()
        Scaffold(
            containerColor = Theme.color.system.white,
            bottomBar = {
                BottomBar(
                    selected = currentTab,
                    onItemSelected = { element ->
                        val current = BottomBarElement.entries.indexOf(currentTab)
                        val target = BottomBarElement.entries.indexOf(element)
                        isForward = target > current
                        currentTab = element
                        internalBackStack.replace(element.navigationKey)
                    },
                    onAddClick = { /* TODO */ },
                )
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NavDisplay(
                    backStack = internalBackStack,
                    transitionSpec = isForward.transitionSpec(),
                    popTransitionSpec = (!isForward).transitionSpec(),
                ) { key ->
                    when (key) {
                        is NavigationHostInternal -> key.graph(
                            backStack = internalBackStack,
                            bottomBarHeight = paddingValues.calculateBottomPadding()
                        )

                        else -> NavEntry(key) { Text("Unknown destination: $key") }
                    }
                }
                //TODO remove
                PrimaryButton(
                    text = "Clean DB".asUiText,
                    style = ButtonType.SMALL,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(Theme.size.s16),
                ) {
                    scope.launch {
                        appStorage.clear()
                        backStack.navigate(
                            key = NavigationOnboarding.Welcome,
                            clearBackStack = true,
                        )
                    }
                }
            }
        }
    }
}

private fun <T : Any> Boolean.transitionSpec(): AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform {
    val towards = when {
        this -> AnimatedContentTransitionScope.SlideDirection.Start
        else -> AnimatedContentTransitionScope.SlideDirection.End
    }
    return {
        slideIntoContainer(
            towards = towards,
            animationSpec = tween(300)
        ) togetherWith slideOutOfContainer(
            towards = towards,
            animationSpec = tween(300)
        )
    }
}
