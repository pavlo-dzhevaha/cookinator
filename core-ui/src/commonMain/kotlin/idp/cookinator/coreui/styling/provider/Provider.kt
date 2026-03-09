package idp.cookinator.coreui.styling.provider

import androidx.compose.runtime.staticCompositionLocalOf
import idp.cookinator.coreui.styling.attribute.ThemeColor
import idp.cookinator.coreui.styling.attribute.ThemeSize
import idp.cookinator.coreui.styling.attribute.ThemeTypography

const val ERROR_TEXT = "No implementation"

internal val LocalThemeColor = staticCompositionLocalOf<ThemeColor> { error(ERROR_TEXT) }

internal val LocalThemeSize = staticCompositionLocalOf<ThemeSize> { error(ERROR_TEXT) }

internal val LocalThemeTypography =
    staticCompositionLocalOf<ThemeTypography> { error(ERROR_TEXT) }
