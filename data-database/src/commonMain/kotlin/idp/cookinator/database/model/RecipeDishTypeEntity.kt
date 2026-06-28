package idp.cookinator.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "recipe_dish_types",
    primaryKeys = ["recipeId", "dishType"],
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["dishType"])],
)
data class RecipeDishTypeEntity(
    val recipeId: Int,
    val dishType: String,
)
