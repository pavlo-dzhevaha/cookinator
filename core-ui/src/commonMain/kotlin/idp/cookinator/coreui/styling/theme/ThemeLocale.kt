package idp.cookinator.coreui.styling.theme

import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.settings_locale_english
import cookinator.localisation.generated.resources.settings_locale_ukrainian
import idp.cookinator.coreui.component.radio.model.RadioViewElement
import idp.cookinator.coreui.styling.theme.ThemeLocale.Companion.default
import idp.cookinator.localisation.UiText
import idp.cookinator.localisation.UiText.Companion.asUiText

/**
 * Represents the available theme locales for the application.
 *
 * This enum defines the different locale options that can be applied to the app's UI. Each locale
 * corresponds to a specific set of language and regional settings that create a cohesive user
 * experience. The [default] locale is used when no specific locale is set or when an unrecognized
 * value is encountered.
 */
enum class ThemeLocale : RadioViewElement {
    US,
    UK;

    val localeTag: String
        get() = name.lowercase()

    override val title: UiText
        get() = when (this) {
            US -> Res.string.settings_locale_english.asUiText
            UK -> Res.string.settings_locale_ukrainian.asUiText
        }

    companion object {
        /**
         * The default [ThemeLocale] to use when no specific locale is set or when an unrecognized
         * value is encountered.
         */
        val default = US

        /**
         * Returns the [ThemeLocale] corresponding to the given [value], or the [default] if the
         * value is not recognized.
         */
        fun parse(value: String): ThemeLocale = entries.find { it.name == value } ?: default
    }
}
