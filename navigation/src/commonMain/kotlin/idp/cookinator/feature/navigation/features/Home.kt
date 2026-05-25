package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(InternalSerializationApi::class)
val featureHomeSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationHome.Home>()
    }
}

@Serializable
sealed interface NavigationHome : NavKey {
    @Serializable
    data object Home : NavigationHome
}
