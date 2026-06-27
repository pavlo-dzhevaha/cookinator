package idp.cookinator.notification.di

import idp.cookinator.notification.AndroidNotificationScheduler
import idp.cookinator.notification.NotificationScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val notificationModule: Module = module {
    includes(notificationCommonModule)
    singleOf(::AndroidNotificationScheduler) bind NotificationScheduler::class
}
