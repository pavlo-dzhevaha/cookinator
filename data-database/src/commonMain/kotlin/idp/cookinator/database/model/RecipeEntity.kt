package idp.cookinator.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import idp.cookinator.model.Recipe
import kotlinx.serialization.json.Json

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
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
    val instructionsJson: String
)

// Extension to map from Domain to Database
fun Recipe.toEntity(): RecipeEntity = RecipeEntity(
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
    instructionsJson = Json.encodeToString(analyzedInstructions)
)

// Extension to map from Database back to Domain
fun RecipeEntity.toDomainModel(): Recipe = Recipe(
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
    }
)
