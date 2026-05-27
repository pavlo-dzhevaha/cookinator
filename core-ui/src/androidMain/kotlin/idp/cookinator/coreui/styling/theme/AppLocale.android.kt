package idp.cookinator.coreui.styling.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import java.util.Locale

@Composable
actual fun AppLocale(
    locale: ThemeLocale,
    content: @Composable (() -> Unit)
) {
    // During preview, we don't want to change the locale as it can cause issues with the preview rendering.
    if (LocalInspectionMode.current) {
        content()
        return
    }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val targetLocale = Locale.forLanguageTag(locale.localeTag)

    Locale.setDefault(targetLocale)
    configuration.setLocale(targetLocale)

    val localizedContext = context.createConfigurationContext(configuration)

    CompositionLocalProvider(
        LocalContext provides localizedContext,
        LocalConfiguration provides configuration,
        content = content
    )
}
