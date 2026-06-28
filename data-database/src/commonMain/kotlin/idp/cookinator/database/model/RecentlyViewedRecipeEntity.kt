package idp.cookinator.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recently_viewed_recipes")
data class RecentlyViewedRecipeEntity(
    @PrimaryKey val recipeId: Int,
    val viewedAt: Long,
)
