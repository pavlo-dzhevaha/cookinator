package idp.cookinator.host.di

import idp.cookinator.feature.onboarding.di.featureOnboardingModule
import idp.cookinator.preferences.di.dataPreferencesModule
import org.koin.core.context.GlobalContext.startKoin

object AppKoin {
    fun initKoin() {
        startKoin {
            modules(
                dataPreferencesModule,
                featureOnboardingModule,
            )
        }
    }
}