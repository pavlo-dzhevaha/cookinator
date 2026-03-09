package idp.cookinator.coreui.styling.attribute

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Represents the size attributes used in the theme, providing a consistent way to define and
 * access size values across the application.
 */
data class ThemeSize(
    val zero: Dp,
    val s4: Dp,
    val s8: Dp,
    val s12: Dp,
    val s16: Dp,
    val s20: Dp,
    val s24: Dp,
)

val attributeThemeSize: ThemeSize = ThemeSize(
    zero = 0.dp,
    s4 = 4.dp,
    s8 = 8.dp,
    s12 = 12.dp,
    s16 = 16.dp,
    s20 = 20.dp,
    s24 = 24.dp,
)
