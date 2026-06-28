package idp.cookinator.feature.allrecipes.model

import androidx.compose.runtime.Composable
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_all_recipes_category_title
import cookinator.localisation.generated.resources.home_trending_title
import idp.cookinator.feature.navigation.features.AllRecipesFilter
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllRecipesFilter.title(): String = when (this) {
    AllRecipesFilter.Trending -> stringResource(Res.string.home_trending_title)
    is AllRecipesFilter.Category -> stringResource(
        Res.string.home_all_recipes_category_title,
        dishType,
    )
}
