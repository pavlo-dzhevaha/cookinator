package idp.cookinator.feature.navigation.features

import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.screen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val featureCreateRecipeSerializer = SerializersModule {
    polymorphic(NavKey::class) {
        screen<NavigationCreateRecipe.Create>()
        screen<NavigationCreateRecipe.Edit>()
        screen<NavigationCreateRecipe.CreateFromRecipe>()
    }
}

@Serializable
sealed interface NavigationCreateRecipe : NavKey {
    @Serializable
    data object Create : NavigationCreateRecipe

    @Serializable
    data class Edit(val userRecipeId: Long) : NavigationCreateRecipe

    @Serializable
    data class CreateFromRecipe(val recipeId: Int) : NavigationCreateRecipe

    private companion object
}
