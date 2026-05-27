package idp.cookinator.coreui.viewmodel.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import idp.cookinator.coreui.viewmodel.StateViewModel
import idp.cookinator.coreui.viewmodel.base.BaseState
import kotlinx.coroutines.CoroutineScope

/**
 * A composable function that provides a convenient way to access the state from a [StateViewModel]
 * and use it in the UI. This function collects the state from the ViewModel using lifecycle-aware
 * collection and provides it to the content lambda along with a CoroutineScope for launching any
 * necessary coroutines. The content lambda is where you can define your UI using the provided
 * state.
 *
 * @param S The type of the state that the ViewModel manages.
 * @param viewModel The instance of the StateViewModel that holds the state to be collected.
 * @param content A composable lambda that takes a CoroutineScope and the current state as
 * parameters, allowing you to build your UI based on the state.
 */
@Composable
fun <S : BaseState> StateProvider(
    viewModel: StateViewModel<S>,
    content: @Composable CoroutineScope.(state: S) -> Unit,
) {
    val state by viewModel.collectStateWithLifecycle()
    val scope = rememberCoroutineScope()

    content(scope, state)
}
