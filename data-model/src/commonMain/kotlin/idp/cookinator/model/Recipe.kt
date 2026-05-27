package idp.cookinator.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?, // Note: This contains HTML tags (like <b>)

    // Dietary tags (Great for UI chips/badges)
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,

    // Detailed lists for the Recipe Detail Screen
    val extendedIngredients: List<Ingredient>,
    val analyzedInstructions: List<Instruction>,
)

@Serializable
data class Ingredient(
    val id: Int?,
    val name: String?,
    val original: String?,
    val image: String?,
)

@Serializable
data class Instruction(
    val name: String?,
    val steps: List<InstructionStep>,
)

@Serializable
data class InstructionStep(
    val number: Int,
    val step: String
)
