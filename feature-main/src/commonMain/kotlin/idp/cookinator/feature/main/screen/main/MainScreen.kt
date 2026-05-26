package idp.cookinator.feature.main.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import idp.cookinator.coreui.component.appbottombar.AppBottomBar
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement
import idp.cookinator.coreui.component.button.core.ButtonType
import idp.cookinator.coreui.component.button.primary.PrimaryButton
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.main.extension.navigationKey
import idp.cookinator.feature.main.navigation.internal.NavigationMainInternal
import idp.cookinator.feature.main.navigation.internal.graph
import idp.cookinator.feature.main.navigation.internal.internalConfiguration
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigate
import idp.cookinator.feature.navigation.extension.navigationTransitionSpec
import idp.cookinator.feature.navigation.extension.pushToTop
import idp.cookinator.feature.navigation.extension.rememberSoloSceneStrategy
import idp.cookinator.feature.navigation.features.NavigationOnboarding
import idp.cookinator.localisation.UiText.Companion.asUiText
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

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

    Scaffold(
        bottomBar = {
            AppBottomBar(
                selected = currentTab,
                onItemSelected = { item ->
                    val current = BottomBarElement.entries.indexOf(currentTab)
                    val target = BottomBarElement.entries.indexOf(item)
                    isForward = target > current
                    currentTab = item
                    internalNavigator.pushToTop(item.navigationKey)
                },
                onAddClick = { /* TODO */ },
            )
        },
    ) { paddingValues ->
        Box {
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
                        bottomBarHeight = paddingValues.calculateBottomPadding()
                    )

                    else -> NavEntry(key) { Text("Unknown destination: $key") }
                }
            }
            //region TODO remove - only for testing purpose
            val scope = rememberCoroutineScope()
            val appStorage = koinInject<AppStorage>()
            PrimaryButton(
                text = "Clean DB".asUiText,
                style = ButtonType.SMALL,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = Theme.size.s16,
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
            ) {
                scope.launch {
                    appStorage.clear()
                }.invokeOnCompletion {
                    navigator.navigate(
                        key = NavigationOnboarding.Welcome,
                        clearBackStack = true,
                    )
                }
            }
            //endregion
        }
    }
}
