package idp.cookinator.host.di

import idp.cookinator.feature.main.di.featureMainModule
import idp.cookinator.feature.onboarding.di.featureOnboardingModule
import idp.cookinator.feature.settings.di.featureSettingsModule
import idp.cookinator.feature.splash.di.featureSplashModule
import idp.cookinator.host.AppViewModel
import idp.cookinator.preferences.di.dataPreferencesModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val hostModule = module {
    viewModelOf(::AppViewModel)
}

val appModules = listOf(
    dataPreferencesModule,
    featureSplashModule,
    hostModule,
    featureOnboardingModule,
    featureMainModule,
    featureSettingsModule,
)

expect fun initKoin(platformModule: Module? = null)