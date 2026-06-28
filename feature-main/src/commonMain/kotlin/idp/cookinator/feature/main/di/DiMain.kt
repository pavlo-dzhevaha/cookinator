package idp.cookinator.feature.main.di

import idp.cookinator.feature.main.screen.home.HomeViewModel
import idp.cookinator.feature.main.screen.notification.NotificationsViewModel
import idp.cookinator.feature.main.screen.profile.ProfileViewModel
import idp.cookinator.feature.main.screen.saved.SavedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureMainModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SavedViewModel)
    viewModelOf(::NotificationsViewModel)
    viewModelOf(::ProfileViewModel)
}
