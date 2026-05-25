package idp.cookinator.preferences.di

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import idp.cookinator.preferences.AppStorage
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.prefs.Preferences

actual val dataPreferencesModule: Module = module {
    single<Settings> {
        val preferences = Preferences.userRoot().node("idp.cookinator.preferences.app_settings")
        PreferencesSettings(preferences)
    }
    singleOf(::AppStorage)
}
