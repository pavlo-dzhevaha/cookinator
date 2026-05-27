package idp.cookinator.coreui.component.radio.model

import idp.cookinator.localisation.UiText
import idp.cookinator.localisation.UiText.Companion.asUiText

/**
 * Interface representing an element in a radio button group.
 *
 * @property title The display name of the radio button element.
 */
interface RadioViewElement {
    val title: UiText

    companion object {
        /**
         * Creates a [RadioViewElement] with the given [name].
         *
         * @param name The display name of the radio button element.
         * @return A [RadioViewElement] instance with the specified name.
         */
        fun create(name: String): RadioViewElement = DefaultRadioViewElement(name.asUiText)
    }
}

private data class DefaultRadioViewElement(
    override val title: UiText,
) : RadioViewElement
