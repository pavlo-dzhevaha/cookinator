package idp.cookinator.coreui.styling.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import idp.cookinator.coreui.styling.attribute.asMaterialColorScheme
import idp.cookinator.coreui.styling.attribute.asMaterialTypography
import idp.cookinator.coreui.styling.attribute.attributeDarkThemeColor
import idp.cookinator.coreui.styling.attribute.attributeLightThemeColor
import idp.cookinator.coreui.styling.attribute.attributeThemeSize
import idp.cookinator.coreui.styling.attribute.attributeTypography
import idp.cookinator.coreui.styling.provider.LocalThemeColor
import idp.cookinator.coreui.styling.provider.LocalThemeSize
import idp.cookinator.coreui.styling.provider.LocalThemeTypography

/**
 * A composable function that provides a custom theme for the app, based on the selected theme.
 *
 * This function sets up various theme attributes such as colors, typography, corner radius, font
 * sizes, icon sizes, line heights, and spacing. The theme is applied using
 * [CompositionLocalProvider], making these attributes available to any composable functions within
 * the [content] block.
 *
 * @param content The composable content that will be rendered with the applied theme. This allows
 * the theme to be applied globally to any child composable.
 */
@Composable
fun AppTheme(
    locale: ThemeLocale = ThemeLocale.default,
    style: ThemeStyle = ThemeStyle.default,
    content: @Composable () -> Unit,
) {
    val systemDark = isSystemInDarkTheme()
    val currentColorPalette = remember(style) {
        when (style) {
            ThemeStyle.LIGHT -> attributeLightThemeColor
            ThemeStyle.DARK -> attributeDarkThemeColor
            ThemeStyle.AUTO -> if (systemDark) attributeDarkThemeColor else attributeLightThemeColor
        }
    }

    val typography = attributeTypography()

    CompositionLocalProvider(
        LocalThemeColor provides currentColorPalette,
        LocalThemeSize provides attributeThemeSize,
        LocalThemeTypography provides typography,
    ) {
        AppLocale(locale) {
            MaterialTheme(
                colorScheme = currentColorPalette.asMaterialColorScheme(),
                typography = typography.asMaterialTypography(),
                content = content,
            )
        }
    }
}

/**
 * An annotation that provides previews for both light and dark modes. This allows developers to
 * see how their composables will look in different themes without having to switch the theme
 * manually.
 *
 * The [LightDarkPreview] annotation can be applied to any composable function, and it will generate
 * two previews: one for light mode and one for dark mode. Each preview will have a background color
 * that matches the respective theme, making it easier to visualize the design in both modes.
 */
@Preview(
    name = "Light Mode",
    uiMode = AndroidUiModes.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFF1F1F1,
)
@Preview(
    name = "Dark Mode",
    uiMode = AndroidUiModes.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF181818,
)
annotation class LightDarkPreview
