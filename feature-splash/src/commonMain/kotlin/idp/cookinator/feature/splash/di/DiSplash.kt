package idp.cookinator.feature.splash.di

import idp.cookinator.feature.splash.screen.SplashScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSplashModule = module {
    viewModelOf(::SplashScreenViewModel)
}
