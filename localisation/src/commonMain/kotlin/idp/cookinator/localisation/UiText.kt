package idp.cookinator.localisation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class Dynamic(val value: String) : UiText
    data class Resource(
        val resource: StringResource,
        val args: List<Any>,
    ): UiText

    companion object {
        val Empty = Dynamic("")

        val UiText.asString @Composable get() = when(this) {
            is Dynamic -> value
            is Resource -> stringResource(resource, *args.toTypedArray())
        }

        fun StringResource.asUiText(vararg args: Any) = Resource(this, args.toList())

        fun String.asUiText() = Dynamic(this)

        fun UiText?.orEmpty() = this ?: Empty
    }
}
