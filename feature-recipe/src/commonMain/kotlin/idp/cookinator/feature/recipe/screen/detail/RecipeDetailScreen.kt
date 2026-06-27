package idp.cookinator.feature.recipe.screen.detail

import androidx.compose.runtime.Composable
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigateUp

@Composable
internal fun RecipeDetailScreen(
    recipeId: Int,
    navigator: Navigator,
) {
    RecipeDetailContent(
        recipeId = recipeId,
        onBack = navigator::navigateUp,
    )
}
