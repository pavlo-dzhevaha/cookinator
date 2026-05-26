package idp.cookinator.coreui.styling.attribute

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Represents the size attributes used in the theme, providing a consistent way to define and
 * access size values across the application.
 */
data class ThemeSize(
    val zero: Dp = 0.dp,
    val s1: Dp = 1.dp,
    val s4: Dp = 4.dp,
    val s6: Dp = 6.dp,
    val s8: Dp = 8.dp,
    val s10: Dp = 10.dp,
    val s12: Dp = 12.dp,
    val s16: Dp = 16.dp,
    val s20: Dp = 20.dp,
    val s24: Dp = 24.dp,
    val s32: Dp = 32.dp,
    val s40: Dp = 40.dp,
    val s48: Dp = 48.dp,
    val s56: Dp = 56.dp,
)

val attributeThemeSize: ThemeSize = ThemeSize()
