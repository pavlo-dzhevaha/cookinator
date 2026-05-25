package idp.cookinator.coreui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A base ViewModel that provides a simple way to manage UI state using StateFlow.
 */
abstract class BaseViewModel<T>(
    initialState: T,
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(initialState)

    /**
     * Exposes the state as a read-only StateFlow to observers. This allows the UI to collect the state and react to changes.
     */
    val stateFlow = mutableStateFlow.asStateFlow()

    /**
     * Gets the current state value. This is a convenience property for accessing the state without collecting the flow.
     */
    protected val state: T
        get() = mutableStateFlow.value

    /**
     * Updates the state using the provided update function. Only emits a new state if it has changed.
     */
    protected fun updateState(update: (T) -> T) {
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
    fun collectStateWithLifecycle(): State<T> = stateFlow.collectAsStateWithLifecycle()
}