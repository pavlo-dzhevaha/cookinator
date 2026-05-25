package idp.cookinator.coreui.viewmodel.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import idp.cookinator.coreui.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun <S, I, E> MviWrapper(
    viewModel: BaseViewModel<S, I, E>,
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
