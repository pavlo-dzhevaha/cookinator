package idp.cookinator.feature.recipe.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationRecipe
import idp.cookinator.feature.recipe.screen.detail.RecipeDetailArgs
import idp.cookinator.feature.recipe.screen.detail.RecipeDetailScreen

fun NavigationRecipe.graph(
    navigator: Navigator,
): NavEntry<NavKey> = when (this) {
    is NavigationRecipe.Detail -> NavEntry(this) {
        RecipeDetailScreen(
            args = RecipeDetailArgs(recipeId = recipeId),
            navigator = navigator,
        )
    }
    is NavigationRecipe.UserDetail -> NavEntry(this) {
        RecipeDetailScreen(
            args = RecipeDetailArgs(userRecipeId = userRecipeId),
            navigator = navigator,
        )
    }
}
