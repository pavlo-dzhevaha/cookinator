package idp.cookinator

import android.app.Application
import android.content.Context
import idp.cookinator.host.di.initKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            module {
                single<Context> { applicationContext }
            }
        )
    }
}
