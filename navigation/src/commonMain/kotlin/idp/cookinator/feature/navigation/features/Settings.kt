package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureSettingsSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationSettings.Settings>()
    }
}

@Serializable
sealed interface NavigationSettings : NavKey {
    @Serializable
    data object Settings : NavigationSettings

    private companion object
}