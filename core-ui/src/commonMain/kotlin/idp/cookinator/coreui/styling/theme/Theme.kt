package idp.cookinator.coreui.styling.theme

import androidx.compose.runtime.Composable
import idp.cookinator.coreui.styling.attribute.ThemeColor
import idp.cookinator.coreui.styling.attribute.ThemeSize
import idp.cookinator.coreui.styling.attribute.ThemeTypography
import idp.cookinator.coreui.styling.provider.LocalThemeColor
import idp.cookinator.coreui.styling.provider.LocalThemeSize
import idp.cookinator.coreui.styling.provider.LocalThemeTypography
import idp.cookinator.coreui.styling.theme.Theme.color
import idp.cookinator.coreui.styling.theme.Theme.size
import idp.cookinator.coreui.styling.theme.Theme.typography

/**
 * Provides access to the theme properties, allowing for consistent styling across the application.
 *
 * @property color The color palette used in the theme.
 * @property size The size attributes used in the theme, such as spacing and dimensions.
 * @property typography The typography styles used in the theme, such as font sizes and weights.
 */
object Theme {
    val color: ThemeColor
        @Composable
        get() = LocalThemeColor.current
    val size: ThemeSize
        @Composable
        get() = LocalThemeSize.current
    val typography: ThemeTypography
        @Composable
        get() = LocalThemeTypography.current
}
