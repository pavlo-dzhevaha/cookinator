package idp.cookinator.notification.di

import idp.cookinator.feature.navigation.PendingRecipeNavigation
import idp.cookinator.notification.FavoriteRecipeNotificationSender
import idp.cookinator.notification.NotificationDeepLinkStore
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val notificationCommonModule: Module = module {
    singleOf(::FavoriteRecipeNotificationSender)
    singleOf(::NotificationDeepLinkStore) bind PendingRecipeNavigation::class
}
