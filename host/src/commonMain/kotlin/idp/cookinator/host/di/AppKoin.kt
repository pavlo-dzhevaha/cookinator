package idp.cookinator.host.di

import idp.cookinator.database.di.dataDatabaseModule
import idp.cookinator.domain.di.domainModule
import idp.cookinator.feature.allrecipes.di.featureAllRecipesModule
import idp.cookinator.feature.main.di.featureMainModule
import idp.cookinator.feature.onboarding.di.featureOnboardingModule
import idp.cookinator.feature.recipe.di.featureRecipeModule
import idp.cookinator.feature.settings.di.featureSettingsModule
import idp.cookinator.feature.splash.di.featureSplashModule
import idp.cookinator.network.di.dataNetworkModule
import idp.cookinator.notification.di.notificationModule
import idp.cookinator.preferences.di.dataPreferencesModule
import org.koin.core.module.Module

val appModules = listOf(
    dataPreferencesModule,
    dataNetworkModule,
    dataDatabaseModule,
    notificationModule,
    domainModule,
    hostModule,
    featureSplashModule,
    featureOnboardingModule,
    featureMainModule,
    featureAllRecipesModule,
    featureRecipeModule,
    featureSettingsModule,
)

expect fun initKoin(platformModule: Module? = null)