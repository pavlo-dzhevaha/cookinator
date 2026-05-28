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
) {
    companion object {
        val stub = Recipe(
            id = 3107,
            title = "expetenda",
            image = "https://img.spoonacular.com/recipes/633080-556x370.jpg",
            readyInMinutes = 1382,
            servings = 8237,
            summary = "ei",
            vegetarian = false,
            vegan = false,
            glutenFree = false,
            dairyFree = false,
            extendedIngredients = listOf(),
            analyzedInstructions = listOf(),
        )

        val stubs = listOf(
            stub,
            stub.copy(id = 3108, title = "expetenda 2"),
            stub.copy(id = 3109, title = "expetenda 3"),
            stub.copy(id = 3110, title = "expetenda 4"),
            stub.copy(id = 3111, title = "expetenda 5"),
        )
    }
}

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
