package idp.cookinator.database.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import idp.cookinator.database.DatabaseManager
import idp.cookinator.database.dao.AppDatabase
import idp.cookinator.database.dao.RecipeDao
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal expect val roomModule: Module

val dataDatabaseModule: Module = module {
    includes(roomModule)

    single<AppDatabase> {
        val builder = get<RoomDatabase.Builder<AppDatabase>>()
        builder.setDriver(BundledSQLiteDriver())
            .build()
    }

    single<RecipeDao> {
        get<AppDatabase>().recipeDao()
    }

    singleOf(::DatabaseManager)
}
