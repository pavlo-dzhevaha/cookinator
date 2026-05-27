package idp.cookinator.coreui.styling.theme

import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.settings_style_auto
import cookinator.localisation.generated.resources.settings_style_dark
import cookinator.localisation.generated.resources.settings_style_light
import idp.cookinator.coreui.component.radio.model.RadioViewElement
import idp.cookinator.coreui.styling.theme.ThemeStyle.Companion.default
import idp.cookinator.localisation.UiText
import idp.cookinator.localisation.UiText.Companion.asUiText

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

    override val title: UiText
        get() = when (this) {
            LIGHT -> Res.string.settings_style_light.asUiText
            DARK -> Res.string.settings_style_dark.asUiText
            AUTO -> Res.string.settings_style_auto.asUiText
        }

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
        fun parse(value: String): ThemeStyle = entries.find { it.name == value } ?: default
    }
}
