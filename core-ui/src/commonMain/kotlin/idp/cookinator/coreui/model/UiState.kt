package idp.cookinator.coreui.model

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import idp.cookinator.coreui.utils.defaultTween

/**
 * Represents the state of a UI component, such as loading, success, or error.
 */
enum class UiState {
    LOADING,
    EMPTY,
    SUCCESS,
    ERROR;

    val isLoading: Boolean
        get() = this == LOADING

    val isEmpty: Boolean
        get() = this == EMPTY

    val isSuccess: Boolean
        get() = this == SUCCESS

    val isError: Boolean
        get() = this == ERROR

    @Composable
    fun Render(
        nonSuccessModifier: Modifier = Modifier,
        loading: @Composable BoxScope.() -> Unit,
        empty: @Composable BoxScope.() -> Unit,
        error: @Composable BoxScope.() -> Unit,
        success: @Composable () -> Unit,
    ) {
        Crossfade(
            targetState = this@UiState,
            animationSpec = defaultTween(),
        ) { uiState ->
            when (uiState) {
                LOADING -> Box(
                    modifier = nonSuccessModifier,
                    contentAlignment = Alignment.Center,
                    content = loading,
                )

                EMPTY -> Box(
                    modifier = nonSuccessModifier,
                    contentAlignment = Alignment.Center,
                    content = empty,
                )

                ERROR -> Box(
                    modifier = nonSuccessModifier,
                    contentAlignment = Alignment.Center,
                    content = error,
                )

                SUCCESS -> success()
            }
        }
    }
}
