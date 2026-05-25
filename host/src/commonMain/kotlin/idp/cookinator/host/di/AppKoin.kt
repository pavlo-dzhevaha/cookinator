package idp.cookinator.host.di

import idp.cookinator.feature.onboarding.di.onboardingModule
import org.koin.core.context.GlobalContext.startKoin

object AppKoin {
    fun initKoin() {
        startKoin {
            modules(
                onboardingModule
            )
        }
    }
}