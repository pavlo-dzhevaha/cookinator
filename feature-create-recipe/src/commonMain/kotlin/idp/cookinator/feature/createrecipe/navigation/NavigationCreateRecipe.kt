package idp.cookinator.feature.createrecipe.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.createrecipe.screen.create.CreateRecipeMode
import idp.cookinator.feature.createrecipe.screen.create.CreateRecipeScreen
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationCreateRecipe

fun NavigationCreateRecipe.graph(
    navigator: Navigator,
): NavEntry<NavKey> = when (this) {
    NavigationCreateRecipe.Create -> NavEntry(this) {
        CreateRecipeScreen(
            mode = CreateRecipeMode.Create,
            navigator = navigator,
        )
    }
    is NavigationCreateRecipe.Edit -> NavEntry(this) {
        CreateRecipeScreen(
            mode = CreateRecipeMode.Edit(userRecipeId),
            navigator = navigator,
        )
    }
    is NavigationCreateRecipe.CreateFromRecipe -> NavEntry(this) {
        CreateRecipeScreen(
            mode = CreateRecipeMode.FromRecipe(recipeId),
            navigator = navigator,
        )
    }
}
