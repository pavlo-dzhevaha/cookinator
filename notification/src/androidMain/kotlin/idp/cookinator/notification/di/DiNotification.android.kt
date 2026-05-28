package idp.cookinator.notification.di

import idp.cookinator.notification.AndroidNotificationScheduler
import idp.cookinator.notification.NotificationScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val notificationModule: Module = module {
    single<NotificationScheduler> { AndroidNotificationScheduler(context = androidContext()) }
}
