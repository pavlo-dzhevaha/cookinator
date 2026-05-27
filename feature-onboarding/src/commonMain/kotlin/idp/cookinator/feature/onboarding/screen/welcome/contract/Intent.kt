package idp.cookinator.feature.onboarding.screen.welcome.contract

import idp.cookinator.coreui.viewmodel.base.BaseIntent

internal sealed interface Intent : BaseIntent {
    object OnContinue : Intent
    object OnChangeLocale : Intent
}
