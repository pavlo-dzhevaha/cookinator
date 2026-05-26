package idp.cookinator.feature.settings.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.component.radio.RadioView
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.settings.screen.contract.Action
import idp.cookinator.feature.settings.screen.contract.Intent
import idp.cookinator.feature.settings.screen.contract.State

@Composable
internal fun SettingsContent(
    modifier: Modifier = Modifier,
    state: State,
    onIntent: (Intent) -> Unit = {},
    onAction: (Action) -> Unit = {},
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Settings",
                leadingIcon = Icons.ArrowLeft,
                onLeadingAction = { onAction(Action.GoBack) },
                modifier = Modifier
                    .statusBarsPadding(),
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            item {
                RadioView(
                    title = "Theme",
                    selected = state.theme,
                    items = ThemeStyle.entries,
                ) { item -> onIntent(Intent.ChangeTheme(item)) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun Preview() = AppTheme {
    SettingsContent(
        state = State.initialState,
    )
}
