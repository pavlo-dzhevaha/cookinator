package idp.cookinator.host.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import idp.cookinator.coreui.styling.theme.ThemeStyle
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import org.koin.compose.koinInject

/**
 * Observes the current theme style from the [AppStorage] and updates the UI accordingly.
 *
 * @return A [State] containing the current [ThemeStyle].
 */
@Composable
internal fun observeThemeStyle(): State<ThemeStyle> {
    val storage = koinInject<AppStorage>()
    val style = remember { mutableStateOf(ThemeStyle.default) }
    LaunchedEffect(Unit) {
        storage.observeCurrentThemeStyle()
            .map { raw -> ThemeStyle.fromStringOrDefault(raw) }
            .collectLatest { theme -> style.value = theme }
    }
    return style
}
