package idp.cookinator.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import idp.cookinator.database.dao.NotificationDao
import idp.cookinator.database.dao.RecipeDao
import idp.cookinator.database.model.LikedRecipeEntity
import idp.cookinator.database.model.NotificationEntity
import idp.cookinator.database.model.RecipeDishTypeEntity
import idp.cookinator.database.model.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
        LikedRecipeEntity::class,
        NotificationEntity::class,
        RecipeDishTypeEntity::class,
    ],
    version = 4,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    abstract fun notificationDao(): NotificationDao
}

// Room 2.7+ uses this to automatically generate the implementation behind the scenes
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
