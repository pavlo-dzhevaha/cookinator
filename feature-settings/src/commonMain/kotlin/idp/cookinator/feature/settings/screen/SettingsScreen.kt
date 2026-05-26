package idp.cookinator.feature.settings.screen

import androidx.compose.runtime.Composable
import idp.cookinator.coreui.viewmodel.base.MviWrapper
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigateUp
import idp.cookinator.feature.settings.screen.contract.Action
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SettingsScreen(
    navigator: Navigator,
    viewModel: SettingsScreenViewModel = koinViewModel(),
) {
    MviWrapper(
        viewModel = viewModel,
        onEvent = { _ -> }
    ) { state ->
        SettingsContent(
            state = state,
            onIntent = viewModel::onIntent,
            onAction = { action ->
                when (action) {
                    Action.GoBack -> navigator.navigateUp()
                }
            },
        )
    }
}
