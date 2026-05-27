package idp.cookinator.coreui.styling.theme

import androidx.compose.runtime.Composable

/**
 * A provider for the application's theme locale. This allows the app to switch between different languages
 * and regional settings based on the user's preferences.
 *
 * The actual implementation of this provider will depend on the platform (Android, JVM, etc.) and will
 * be defined in separate files (e.g., LocalAppLocale.android.kt, LocalAppLocale.jvm.kt).
 *
 * @param locale The [ThemeLocale] to be provided to the content.
 * @param content The composable content that will have access to the provided locale.
 */
@Composable
expect fun AppLocale(
    locale: ThemeLocale,
    content: @Composable () -> Unit,
)
