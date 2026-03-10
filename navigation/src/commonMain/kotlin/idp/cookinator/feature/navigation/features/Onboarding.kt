package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(InternalSerializationApi::class)
val featureOnboardingSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationOnboarding.Welcome>()
        screen<NavigationOnboarding.Tutorial>()
    }
}

@Serializable
sealed interface NavigationOnboarding : NavKey {
    @Serializable
    data object Welcome : NavigationOnboarding

    @Serializable
    data object Tutorial : NavigationOnboarding
}
