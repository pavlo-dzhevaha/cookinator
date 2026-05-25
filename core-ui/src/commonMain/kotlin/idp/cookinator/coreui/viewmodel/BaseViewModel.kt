package idp.cookinator.coreui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T>(
    initialState: T,
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(initialState)
    val stateFlow = mutableStateFlow.asStateFlow()

    protected val state: T
        get() = mutableStateFlow.value

    protected fun updateState(update: (T) -> T) {
        mutableStateFlow.value = update(state)
    }

    @Composable
    fun collectState(): State<T> = stateFlow.collectAsStateWithLifecycle()
}