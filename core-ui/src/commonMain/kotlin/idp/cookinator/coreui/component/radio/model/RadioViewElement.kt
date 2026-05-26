package idp.cookinator.coreui.component.radio.model

/**
 * Interface representing an element in a radio button group.
 *
 * @property name The display name of the radio button element.
 */
interface RadioViewElement {
    val name: String

    companion object {
        /**
         * Creates a [RadioViewElement] with the given [name].
         *
         * @param name The display name of the radio button element.
         * @return A [RadioViewElement] instance with the specified name.
         */
        fun create(name: String): RadioViewElement = DefaultRadioViewElement(name)
    }
}

private data class DefaultRadioViewElement(
    override val name: String,
) : RadioViewElement
