package idp.cookinator.feature.onboarding.di

import idp.cookinator.feature.onboarding.screen.welcome.WelcomeScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule = module {
    viewModelOf(::WelcomeScreenViewModel)
}
