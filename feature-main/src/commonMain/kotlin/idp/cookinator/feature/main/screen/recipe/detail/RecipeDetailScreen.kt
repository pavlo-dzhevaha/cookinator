package idp.cookinator.feature.main.screen.recipe.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigateUp

@Composable
internal fun RecipeDetailScreen(
    recipeId: Int,
    internalNavigator: Navigator,
    bottomBarHeight: Dp,
) {
    RecipeDetailContent(
        recipeId = recipeId,
        bottomBarHeight = bottomBarHeight,
        onBack = internalNavigator::navigateUp,
    )
}
