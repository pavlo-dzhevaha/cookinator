package idp.cookinator.coreui.styling.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
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
    style: ThemeStyle = ThemeStyle.LIGHT,
    content: @Composable () -> Unit,
) {
    val currentColorPalette = remember(style) {
        when (style) {
            ThemeStyle.LIGHT -> attributeLightThemeColor
            ThemeStyle.DARK -> attributeDarkThemeColor
        }
    }

    val typography = attributeTypography()

    CompositionLocalProvider(
        LocalThemeColor provides currentColorPalette,
        LocalThemeSize provides attributeThemeSize,
        LocalThemeTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = currentColorPalette.asMaterialColorScheme(),
            typography = typography.asMaterialTypography(),
            content = content,
        )
    }
}
