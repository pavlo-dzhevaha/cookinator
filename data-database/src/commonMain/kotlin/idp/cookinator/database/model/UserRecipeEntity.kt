package idp.cookinator.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import idp.cookinator.model.UserRecipe
import kotlinx.serialization.json.Json

@Entity(tableName = "user_recipes")
data class UserRecipeEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val image: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val ingredientsJson: String,
    val instructionsJson: String,
    val dishTypesJson: String,
    val createdAt: Long,
)

fun UserRecipe.toEntity(): UserRecipeEntity = UserRecipeEntity(
    id = id,
    title = title,
    image = image,
    readyInMinutes = readyInMinutes,
    servings = servings,
    summary = summary,
    vegetarian = vegetarian,
    vegan = vegan,
    glutenFree = glutenFree,
    dairyFree = dairyFree,
    ingredientsJson = Json.encodeToString(extendedIngredients),
    instructionsJson = Json.encodeToString(analyzedInstructions),
    dishTypesJson = Json.encodeToString(dishTypes),
    createdAt = createdAt,
)

fun UserRecipeEntity.toDomainModel(): UserRecipe = UserRecipe(
    id = id,
    title = title,
    image = image,
    readyInMinutes = readyInMinutes,
    servings = servings,
    summary = summary,
    vegetarian = vegetarian,
    vegan = vegan,
    glutenFree = glutenFree,
    dairyFree = dairyFree,
    extendedIngredients = when {
        ingredientsJson.isNotEmpty() -> Json.decodeFromString(ingredientsJson)
        else -> emptyList()
    },
    analyzedInstructions = when {
        instructionsJson.isNotEmpty() -> Json.decodeFromString(instructionsJson)
        else -> emptyList()
    },
    dishTypes = when {
        dishTypesJson.isNotEmpty() -> Json.decodeFromString(dishTypesJson)
        else -> emptyList()
    },
    createdAt = createdAt,
)
