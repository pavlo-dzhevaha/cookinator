package idp.cookinator.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDraft(
    val imagePath: String? = null,
    val title: String = "",
    val servings: Int = 1,
    val readyInMinutes: Int = 5,
    val vegetarian: Boolean = false,
    val vegan: Boolean = false,
    val glutenFree: Boolean = false,
    val dairyFree: Boolean = false,
    val selectedCategories: List<String> = emptyList(),
    val ingredients: List<RecipeDraftIngredient> = listOf(RecipeDraftIngredient()),
)

@Serializable
data class RecipeDraftIngredient(
    val localId: String = "",
    val name: String = "",
    val description: String = "",
)
