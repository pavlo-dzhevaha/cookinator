package idp.cookinator.feature.allrecipes.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import idp.cookinator.feature.allrecipes.screen.AllRecipesScreen
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.features.NavigationAllRecipes

fun NavigationAllRecipes.graph(
    navigator: Navigator,
): NavEntry<NavKey> = when (this) {
    is NavigationAllRecipes.All -> NavEntry(this) {
        AllRecipesScreen(
            filter = filter,
            navigator = navigator,
        )
    }
}
