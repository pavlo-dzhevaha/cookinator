package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureMainSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationMain.Main>()
    }
}

@Serializable
sealed interface NavigationMain : NavKey {
    @Serializable
    data object Main : NavigationMain

    private companion object
}
