package idp.cookinator.feature.settings.di

import idp.cookinator.feature.settings.screen.SettingsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureSettingsModule = module {
    viewModelOf(::SettingsScreenViewModel)
}
