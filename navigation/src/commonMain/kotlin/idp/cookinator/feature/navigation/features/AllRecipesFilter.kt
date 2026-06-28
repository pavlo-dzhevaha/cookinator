package idp.cookinator.feature.navigation.features

import kotlinx.serialization.Serializable

@Serializable
sealed interface AllRecipesFilter {
    @Serializable
    data object Trending : AllRecipesFilter

    @Serializable
    data class Category(val dishType: String) : AllRecipesFilter
}
