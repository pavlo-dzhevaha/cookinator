package idp.cookinator.coreui.styling.theme

import androidx.compose.runtime.Composable
import java.util.Locale

@Composable
actual fun AppLocale(
    locale: ThemeLocale,
    content: @Composable () -> Unit,
) {
    val targetLocale = Locale.forLanguageTag(locale.localeTag)
    Locale.setDefault(targetLocale)
    content()
}
