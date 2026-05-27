package idp.cookinator.network.model

import idp.cookinator.model.Recipe
import idp.cookinator.network.extension.compactMap
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

fun RandomRecipesResponse.toDomainModels(): List<Recipe> = recipes.map { recipe ->
    Recipe(
        id = recipe.id,
        title = recipe.title,
        image = recipe.image,
        readyInMinutes = recipe.readyInMinutes,
        servings = recipe.servings,
        summary = recipe.summary,
        vegetarian = recipe.vegetarian ?: false,
        vegan = recipe.vegan ?: false,
        glutenFree = recipe.glutenFree ?: false,
        dairyFree = recipe.dairyFree ?: false,
        extendedIngredients = recipe.extendedIngredients.compactMap { ingredient ->
            idp.cookinator.model.Ingredient(
                id = ingredient.id,
                name = ingredient.name,
                original = ingredient.original,
                image = ingredient.image
            )
        },
        analyzedInstructions = recipe.analyzedInstructions.compactMap { instruction ->
            idp.cookinator.model.Instruction(
                name = instruction.name,
                steps = instruction.steps.compactMap { step ->
                    idp.cookinator.model.InstructionStep(
                        number = step.number,
                        step = step.step
                    )
                },
            )
        }
    )
}
