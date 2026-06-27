package idp.cookinator.host.di

import idp.cookinator.logging.AppLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

actual fun initKoin(platformModule: Module?) {
    AppLogger.init()
    startKoin {
        modules(appModules)
    }
}