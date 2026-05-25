package idp.cookinator.coreui.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * A base ViewModel that provides a simple way to manage UI state using StateFlow.
 */
abstract class BaseViewModel<S, I, E>(
    initialState: S,
) : ViewModel() {
    //region State management

    /**
     * A MutableStateFlow that holds the current state. This is private to prevent external modification.
     */
    private val mutableStateFlow = MutableStateFlow(initialState)

    /**
     * Exposes the state as a read-only StateFlow to observers. This allows the UI to collect the state and react to changes.
     */
    val stateFlow = mutableStateFlow.asStateFlow()

    /**
     * Gets the current state value. This is a convenience property for accessing the state without collecting the flow.
     */
    protected val state: S
        get() = mutableStateFlow.value

    /**
     * Updates the state using the provided update function. Only emits a new state if it has changed.
     */
    protected fun updateState(update: (S) -> S) {
        val oldState = state
        val newState = update(oldState)
        if (newState != oldState) {
            mutableStateFlow.value = newState
        }
    }

    /**
     * Collects the state as a Compose State, automatically handling lifecycle awareness.
     */
    @Composable
    fun collectStateWithLifecycle() = stateFlow.collectAsStateWithLifecycle()

    //endregion

    //region Event management

    /**
     * Event channel for one-time events. This is useful for actions that should only be handled
     * once, such as navigation or showing a toast. The events are sent through the channel and can
     * be collected as a flow in the UI.
     */
    private val eventChannel = Channel<E>()

    /**
     * Sends an event to the event channel. This can be called from the ViewModel to trigger
     * one-time events in the UI.
     */
    val events = eventChannel.receiveAsFlow()

    /**
     * A helper function to send an event. This can be used in the ViewModel to trigger events
     * without directly accessing the channel.
     */
    protected fun sendEvent(event: E) = launch { eventChannel.send(event) }

    /**
     * A helper function to try sending an event without suspending. This can be used for events
     * that are not critical and can be dropped if the channel is full.
     */
    protected fun trySendEvent(event: E) {
        eventChannel.trySend(event).onFailure { e ->
            // TODO log dropped event
        }
    }

    //endregion

    //region Intent management

    /**
     * An abstract function to handle intents. This should be implemented by subclasses to define
     * how the ViewModel responds to different intents from the UI.
     */
    abstract fun onIntent(intent: I)

    //endregion

    //region Extensions

    /**
     * A CoroutineExceptionHandler that can be used to catch and handle exceptions in coroutines
     * launched from the ViewModel. This can be used to log crashes or show error messages to the
     * user.
     */
    private val crashHandler = CoroutineExceptionHandler { _, e ->
        // TODO log crash
    }

    /**
     * A helper function to launch a coroutine with the crash handler. This can be used to ensure
     * that all coroutines launched from the ViewModel are properly handled for exceptions.
     */
    protected fun launch(block: suspend () -> Unit) = viewModelScope.launch(
        context = crashHandler,
    ) { block() }

    //endregion

    override fun onCleared() {
        eventChannel.close()
        super.onCleared()
    }
}