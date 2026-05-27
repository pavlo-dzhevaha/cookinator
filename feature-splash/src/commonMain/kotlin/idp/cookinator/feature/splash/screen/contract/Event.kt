package idp.cookinator.feature.splash.screen.contract

import androidx.navigation3.runtime.NavKey
import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface Event : BaseEvent {
    data class NavigateToStartDestination(
        val startDestination: NavKey,
    ) : Event
}
