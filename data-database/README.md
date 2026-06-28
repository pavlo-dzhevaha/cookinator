# :data-database

Local persistence via Room (SQLite).

## Purpose

Stores discovery recipes, likes, recently viewed items, in-app notifications, and user-created recipes. Exposes typed manager facades as the data-layer boundary for upper layers.

## Key classes / files

- `AppDatabase.kt` — Room database (v6) + platform `AppDatabaseConstructor`
- `dao/RecipeDao.kt`, `dao/NotificationDao.kt`, `dao/UserRecipeDao.kt`
- `model/*Entity.kt` — Room entities and `toDomainModel()` / `toEntity()` mappers
- `RecipeDatabaseManager.kt` — discovery recipes, likes, dish types, recently viewed
- `NotificationDatabaseManager.kt` — observe/insert/mark-read/clear notifications
- `UserRecipeDatabaseManager.kt` — CRUD/observe user-created recipes
- `di/DiDatabase.kt` — Koin `dataDatabaseModule` + platform `roomModule`

## Dependencies

**Project modules:** `:data-model`

**Libraries:** Koin, Room (KSP), SQLite bundled driver, kotlinx serialization (JSON columns)

## Public API / usage

- `RecipeDatabaseManager`, `NotificationDatabaseManager`, `UserRecipeDatabaseManager` — consumed by `:domain` and `:notification`
- `dataDatabaseModule` — registered in `host/di/AppKoin.kt`
- DAOs and entities are public Kotlin types but intended for internal use within managers
