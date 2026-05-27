package idp.cookinator.feature.onboarding.screen.welcome.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent

internal sealed interface Event : BaseEvent {
    object Continue : Event
}
