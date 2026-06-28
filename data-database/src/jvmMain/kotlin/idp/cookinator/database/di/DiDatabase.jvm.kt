package idp.cookinator.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import idp.cookinator.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

internal actual val roomModule: Module = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "recipes.db")

        Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath
        )
    }
}
