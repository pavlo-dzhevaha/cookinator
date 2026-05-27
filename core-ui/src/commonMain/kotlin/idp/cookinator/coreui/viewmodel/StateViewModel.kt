package idp.cookinator.coreui.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A base ViewModel class that manages a state of type [S] using a [MutableStateFlow]. This class
 * provides a simple way to manage UI state and expose it as a read-only [StateFlow] to the UI. It
 * also includes a helper function to update the state and a coroutine exception handler to catch
 * and log crashes. Subclasses can extend this ViewModel to manage their specific state and provide
 * additional functionality as needed.
 *
 * @param S The type of the state that this ViewModel manages.
 * @param initialState The initial state value that the ViewModel will start with.
 */
abstract class StateViewModel<S>(
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
    protected fun updateState(update: (S) -> S) = mutableStateFlow.update(update)

    /**
     * Collects the state as a Compose State, automatically handling lifecycle awareness.
     */
    @Composable
    fun collectStateWithLifecycle() = stateFlow.collectAsStateWithLifecycle()

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
}