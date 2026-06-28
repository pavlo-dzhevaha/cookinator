package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureRecipeSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationRecipe.Detail>()
        screen<NavigationRecipe.UserDetail>()
    }
}

@Serializable
sealed interface NavigationRecipe : NavKey {
    @Serializable
    data class Detail(val recipeId: Int) : NavigationRecipe

    @Serializable
    data class UserDetail(val userRecipeId: Long) : NavigationRecipe

    private companion object
}
