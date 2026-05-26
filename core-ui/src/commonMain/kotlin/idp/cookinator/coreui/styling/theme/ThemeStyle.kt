package idp.cookinator.coreui.styling.theme

import idp.cookinator.coreui.component.radio.model.RadioViewElement
import idp.cookinator.coreui.styling.theme.ThemeStyle.Companion.default

/**
 * Represents the available theme styles for the application.
 *
 * This enum defines the different visual themes that can be applied to the app's UI. Each style
 * corresponds to a specific set of colors, typography, and other design elements that create a
 * cohesive look and feel. The [default] style is used when no specific theme is set or when an
 * unrecognized value is encountered.
 */
enum class ThemeStyle : RadioViewElement {
    LIGHT,
    DARK,
    AUTO;

    companion object {
        /**
         * The default [ThemeStyle] to use when no specific style is set or when an unrecognized
         * value is encountered.
         */
        val default = AUTO

        /**
         * Returns the [ThemeStyle] corresponding to the given [value], or the [default] if the
         * value is not recognized.
         */
        fun fromStringOrDefault(value: String): ThemeStyle = when (value) {
            LIGHT.name -> LIGHT
            DARK.name -> DARK
            AUTO.name -> AUTO
            else -> default
        }
    }
}
