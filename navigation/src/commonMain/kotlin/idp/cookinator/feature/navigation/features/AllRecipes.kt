package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureAllRecipesSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationAllRecipes.All>()
    }
}

@Serializable
sealed interface NavigationAllRecipes : NavKey {
    @Serializable
    data class All(val section: HomeRecipeSection) : NavigationAllRecipes

    private companion object
}
