package idp.cookinator.preferences.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.prefs.Preferences

@OptIn(ExperimentalSettingsApi::class)
actual val dataPreferencesModule: Module = module {
    single<FlowSettings> {
        val preferences = Preferences.userRoot().node("idp.cookinator.$appStorageName")

        val observableSettings = PreferencesSettings(preferences)
        observableSettings.toFlowSettings(Dispatchers.IO)
    }
    singleOf(::AppStorage)
}
