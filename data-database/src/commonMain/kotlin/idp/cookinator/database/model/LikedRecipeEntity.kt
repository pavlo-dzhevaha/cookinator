package idp.cookinator.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_recipes")
data class LikedRecipeEntity(
    @PrimaryKey val recipeId: Int
)
