package idp.cookinator.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomRecipesResponse(
    val recipes: List<RecipeResponse>
)

@Serializable
data class RecipeResponse(
    val id: Int,
    val title: String,
    val image: String? = null,
    val readyInMinutes: Int? = null,
    val servings: Int? = null,
    val summary: String? = null, // Note: This contains HTML tags (like <b>)

    // Dietary tags (Great for UI chips/badges)
    val vegetarian: Boolean? = null,
    val vegan: Boolean? = null,
    val glutenFree: Boolean? = null,
    val dairyFree: Boolean? = null,

    // Detailed lists for the Recipe Detail Screen
    val extendedIngredients: List<Ingredient?>? = null,
    val analyzedInstructions: List<Instruction?>? = null,
)

@Serializable
data class Ingredient(
    val id: Int? = null,
    val name: String? = null,
    // "original" contains the full readable string (e.g., "1 carrot, grated")
    val original: String? = null,
    // Spoonacular only returns the filename here (e.g., "sliced-carrot.png")
    val image: String? = null,
)

@Serializable
data class Instruction(
    val name: String? = null,
    val steps: List<InstructionStep?>? = null,
)

@Serializable
data class InstructionStep(
    val number: Int,
    val step: String
)
