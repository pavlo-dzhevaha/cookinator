package idp.cookinator.feature.main.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import idp.cookinator.coreui.component.appbottombar.AppBottomBar
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement
import idp.cookinator.feature.main.extension.navigationKey
import idp.cookinator.feature.main.navigation.internal.NavigationMainInternal
import idp.cookinator.feature.main.navigation.internal.graph
import idp.cookinator.feature.main.navigation.internal.internalConfiguration
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigationTransitionSpec
import idp.cookinator.feature.navigation.extension.pushToTop
import idp.cookinator.feature.navigation.extension.rememberSoloSceneStrategy

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun MainScreen(
    navigator: Navigator,
    initialTab: BottomBarElement = BottomBarElement.Home,
) {
    val internalNavigator = rememberNavBackStack(
        configuration = internalConfiguration,
        initialTab.navigationKey,
    )
    var currentTab by rememberSaveable { mutableStateOf(initialTab) }
    var isForward by remember { mutableStateOf(true) }

    LaunchedEffect(internalNavigator.lastOrNull()) {
        val entry = internalNavigator.lastOrNull()
        val targetTab = BottomBarElement.entries.find { it.navigationKey == entry }
        if (targetTab != null && targetTab != currentTab) {
            val current = BottomBarElement.entries.indexOf(currentTab)
            val target = BottomBarElement.entries.indexOf(targetTab)
            isForward = target > current
            currentTab = targetTab
        }
    }

    Scaffold(
        bottomBar = {
            AppBottomBar(
                selected = currentTab,
                onItemSelected = { item ->
                    internalNavigator.pushToTop(item.navigationKey)
                },
                onAddClick = { /* TODO */ },
            )
        },
    ) { paddingValues ->
        NavDisplay(
            backStack = internalNavigator,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            sceneStrategies = listOf(
                rememberSoloSceneStrategy(),
            ),
            transitionSpec = navigationTransitionSpec(isForward),
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
        ) { key ->
            when (key) {
                is NavigationMainInternal -> key.graph(
                    navigator = navigator,
                    internalNavigator = internalNavigator,
                    bottomBarHeight = paddingValues.calculateBottomPadding()
                )

                else -> NavEntry(key) { Text("Unknown destination: $key") }
            }
        }
    }
}
