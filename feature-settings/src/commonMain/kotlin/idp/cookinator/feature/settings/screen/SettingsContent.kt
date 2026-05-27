package idp.cookinator.feature.settings.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.settings_locale_title
import cookinator.localisation.generated.resources.settings_style_title
import cookinator.localisation.generated.resources.settings_title
import idp.cookinator.coreui.component.apptopbar.AppTopBar
import idp.cookinator.coreui.component.radio.RadioView
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.ThemeLocale
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.feature.settings.screen.contract.Action
import idp.cookinator.feature.settings.screen.contract.Intent
import idp.cookinator.feature.settings.screen.contract.State
import org.jetbrains.compose.resources.stringResource

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
                title = stringResource(Res.string.settings_title),
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
                    title = stringResource(Res.string.settings_style_title),
                    selected = state.style,
                    items = ThemeStyle.entries,
                ) { item -> onIntent(Intent.ChangeStyle(item)) }
            }
            item {
                RadioView(
                    title = stringResource(Res.string.settings_locale_title),
                    selected = state.locale,
                    items = ThemeLocale.entries,
                ) { item -> onIntent(Intent.ChangeLocale(item)) }
            }
        }
    }
}

@LightDarkPreview
@Composable
internal fun Preview() = AppTheme {
    SettingsContent(
        state = State.initialState,
    )
}
