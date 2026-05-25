package idp.cookinator.preferences.di

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import idp.cookinator.preferences.AppStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val dataPreferencesModule: Module = module {
    single<Settings> {
        val sharedPrefs =
            androidContext().getSharedPreferences("my_app_settings", Context.MODE_PRIVATE)
        SharedPreferencesSettings(sharedPrefs)
    }
    singleOf(::AppStorage)
}
