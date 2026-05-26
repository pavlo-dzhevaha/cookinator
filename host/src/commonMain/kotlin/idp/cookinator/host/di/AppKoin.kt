package idp.cookinator.host.di

import idp.cookinator.feature.main.di.featureMainModule
import idp.cookinator.feature.onboarding.di.featureOnboardingModule
import idp.cookinator.feature.settings.di.featureSettingsModule
import idp.cookinator.feature.splash.di.featureSplashModule
import idp.cookinator.preferences.di.dataPreferencesModule
import org.koin.core.module.Module

val appModules = listOf(
    dataPreferencesModule,
    featureSplashModule,
    featureOnboardingModule,
    featureMainModule,
    featureSettingsModule,
)

expect fun initKoin(platformModule: Module? = null)