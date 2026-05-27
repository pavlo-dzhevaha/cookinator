package idp.cookinator.coreui.viewmodel.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import idp.cookinator.coreui.viewmodel.MviViewModel
import idp.cookinator.coreui.viewmodel.base.BaseEvent
import idp.cookinator.coreui.viewmodel.base.BaseIntent
import idp.cookinator.coreui.viewmodel.base.BaseState
import kotlinx.coroutines.CoroutineScope

/**
 * A composable function that provides a convenient way to access the state from an [MviViewModel]
 * and handle events emitted by the ViewModel. This function collects the state from the ViewModel
 * using lifecycle-aware collection and provides it to the content lambda along with a
 * CoroutineScope for launching any necessary coroutines. It also collects events emitted by the
 * ViewModel and passes them to the provided onEvent lambda for handling. The content lambda is
 * where you can define your UI using the provided state.
 *
 * @param S The type of the state that the ViewModel manages.
 * @param I The type of intents that the ViewModel accepts.
 * @param E The type of events that the ViewModel emits.
 * @param viewModel The instance of the MviViewModel that holds the state and emits events to be
 * collected.
 * @param onEvent A lambda function that takes an event of type E as a parameter, allowing you to
 * handle events emitted by the ViewModel.
 * @param content A composable lambda that takes a CoroutineScope and the current state as
 * parameters, allowing you to build your UI based on the state.
 */
@Composable
fun <S : BaseState, I : BaseIntent, E : BaseEvent> MviStateProvider(
    viewModel: MviViewModel<S, I, E>,
    onEvent: (E) -> Unit,
    content: @Composable CoroutineScope.(state: S) -> Unit,
) {
    val state by viewModel.collectStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            onEvent(event)
        }
    }

    content(scope, state)
}
