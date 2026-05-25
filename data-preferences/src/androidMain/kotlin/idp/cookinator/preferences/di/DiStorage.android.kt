package idp.cookinator.preferences.di

import android.content.Context
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
actual val dataPreferencesModule: Module = module {
    single<FlowSettings> {
        val sharedPrefs =
            androidContext().getSharedPreferences(appStorageName, Context.MODE_PRIVATE)

        val observableSettings = SharedPreferencesSettings(sharedPrefs)
        observableSettings.toFlowSettings(Dispatchers.IO)
    }
    singleOf(::AppStorage)
}
