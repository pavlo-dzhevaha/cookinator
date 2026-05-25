package idp.cookinator.feature.onboarding.screen.welcome.contract

sealed interface WelcomeScreenEvent {
    object Continue : WelcomeScreenEvent
}
