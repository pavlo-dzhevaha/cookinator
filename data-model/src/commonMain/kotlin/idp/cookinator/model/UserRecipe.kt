package idp.cookinator.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRecipe(
    val id: Long,
    val title: String,
    val image: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String? = null,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val dishTypes: List<String> = emptyList(),
    val extendedIngredients: List<Ingredient>,
    val analyzedInstructions: List<Instruction> = emptyList(),
    val createdAt: Long,
) {
    fun toRecipe(): Recipe = Recipe(
        id = (id % Int.MAX_VALUE).toInt(),
        title = title,
        image = image,
        readyInMinutes = readyInMinutes,
        servings = servings,
        summary = summary,
        vegetarian = vegetarian,
        vegan = vegan,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        dishTypes = dishTypes,
        extendedIngredients = extendedIngredients,
        analyzedInstructions = analyzedInstructions,
    )
}
