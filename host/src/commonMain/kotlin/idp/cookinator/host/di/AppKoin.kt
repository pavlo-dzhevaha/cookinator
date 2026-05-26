package idp.cookinator.host.di

import idp.cookinator.feature.home.di.featureHomeModule
import idp.cookinator.feature.onboarding.di.featureOnboardingModule
import idp.cookinator.feature.splash.di.featureSplashModule
import idp.cookinator.preferences.di.dataPreferencesModule
import org.koin.core.module.Module

val appModules = listOf(
    dataPreferencesModule,
    featureSplashModule,
    featureOnboardingModule,
    featureHomeModule,
)

expect fun initKoin(platformModule: Module? = null)