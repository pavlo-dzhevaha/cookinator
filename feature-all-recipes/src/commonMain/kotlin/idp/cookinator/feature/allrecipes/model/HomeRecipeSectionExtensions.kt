package idp.cookinator.feature.allrecipes.model

import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.home_trending_title
import idp.cookinator.feature.navigation.features.HomeRecipeSection
import org.jetbrains.compose.resources.StringResource

fun HomeRecipeSection.titleRes(): StringResource = when (this) {
    HomeRecipeSection.Trending -> Res.string.home_trending_title
}
