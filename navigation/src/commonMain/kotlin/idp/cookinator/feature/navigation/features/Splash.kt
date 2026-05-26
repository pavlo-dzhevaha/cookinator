package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureSplashSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationSplash.Splash>()
    }
}

@Serializable
sealed interface NavigationSplash : NavKey {
    @Serializable
    data object Splash : NavigationSplash

    private companion object
}