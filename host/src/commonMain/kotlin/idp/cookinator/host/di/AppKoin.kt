package idp.cookinator.host.di

import idp.cookinator.feature.home.di.featureHomeModule
import idp.cookinator.feature.onboarding.di.featureOnboardingModule
import idp.cookinator.feature.splash.di.featureSplashModule
import idp.cookinator.preferences.di.dataPreferencesModule
import org.koin.core.context.GlobalContext.startKoin

object AppKoin {
    fun initKoin() {
        startKoin {
            modules(
                dataPreferencesModule,
                featureSplashModule,
                featureOnboardingModule,
                featureHomeModule,
            )
        }
    }
}