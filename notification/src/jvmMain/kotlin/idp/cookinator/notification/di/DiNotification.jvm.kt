package idp.cookinator.notification.di

import idp.cookinator.notification.DesktopNotificationScheduler
import idp.cookinator.notification.NotificationScheduler
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val notificationModule: Module = module {
    includes(notificationCommonModule)
    singleOf(::DesktopNotificationScheduler) bind NotificationScheduler::class
}
