package idp.cookinator.host.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

actual fun initKoin(platformModule: Module?) {
    startKoin {
        modules(appModules)
    }
}