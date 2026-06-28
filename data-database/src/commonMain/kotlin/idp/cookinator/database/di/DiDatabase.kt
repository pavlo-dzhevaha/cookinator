package idp.cookinator.database.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import idp.cookinator.database.AppDatabase
import idp.cookinator.database.NotificationDatabaseManager
import idp.cookinator.database.RecipeDatabaseManager
import idp.cookinator.database.UserRecipeDatabaseManager
import idp.cookinator.database.dao.NotificationDao
import idp.cookinator.database.dao.RecipeDao
import idp.cookinator.database.dao.UserRecipeDao
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal expect val roomModule: Module

val dataDatabaseModule: Module = module {
    includes(roomModule)

    single<AppDatabase> {
        val builder = get<RoomDatabase.Builder<AppDatabase>>()
        builder
            .setDriver(BundledSQLiteDriver())
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single<RecipeDao> {
        get<AppDatabase>().recipeDao()
    }

    single<NotificationDao> {
        get<AppDatabase>().notificationDao()
    }

    single<UserRecipeDao> {
        get<AppDatabase>().userRecipeDao()
    }

    singleOf(::RecipeDatabaseManager)
    singleOf(::NotificationDatabaseManager)
    singleOf(::UserRecipeDatabaseManager)
}
