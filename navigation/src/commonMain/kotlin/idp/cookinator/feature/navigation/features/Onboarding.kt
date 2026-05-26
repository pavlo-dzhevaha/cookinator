package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureOnboardingSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationOnboarding.Welcome>()
    }
}

@Serializable
sealed interface NavigationOnboarding : NavKey {
    @Serializable
    data object Welcome : NavigationOnboarding

    private companion object
}
