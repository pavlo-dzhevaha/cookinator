package idp.cookinator.coreui.viewmodel

import idp.cookinator.coreui.viewmodel.base.BaseEvent
import idp.cookinator.coreui.viewmodel.base.BaseIntent
import idp.cookinator.coreui.viewmodel.base.BaseState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * A base ViewModel class for implementing the MVI (Model-View-Intent) architecture pattern. This
 * class manages the state of the UI and handles one-time events through a channel. Subclasses
 * should implement the [onIntent] function to define how the ViewModel responds to different
 * intents from the UI. The state is managed using the [StateViewModel] base class, and events are
 * sent through the [eventChannel] and can be collected as a flow in the UI.
 *
 * @param S The type of the state that the ViewModel manages.
 * @param I The type of the intents that the ViewModel handles.
 * @param E The type of the events that the ViewModel emits.
 * @param initialState The initial state of the ViewModel.
 */
abstract class MviViewModel<S : BaseState, I : BaseIntent, E : BaseEvent>(
    initialState: S,
) : StateViewModel<S>(initialState) {
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

    override fun onCleared() {
        eventChannel.close()
        super.onCleared()
    }
}