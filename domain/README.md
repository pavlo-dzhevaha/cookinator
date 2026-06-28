# :domain

Business logic layer for recipes, engagement, drafts, and notifications.

## Purpose

Orchestrates recipe discovery, user recipes, likes, drafts, and in-app notifications. Use cases sit above `:data-*` modules and expose cache-first / offline-first flows.

## Key classes / files

- `DiDomain.kt` — Koin `domainModule`
- `RecipeReminderSender` — port implemented by `:notification`
- `internal/UseCaseIo.kt` — `useCaseIo`, `useCaseLog` helpers
- **Discovery:** `SyncDiscoveryRecipesUseCase`, `ObserveDiscoveryRecipesUseCase`, `GetRecipeByIdUseCase`, `ObserveRecipeDishTypesUseCase`, `ObserveRecipesByDishTypeUseCase`
- **Engagement:** `SetRecipeLikedUseCase`, `ObserveLikedRecipesUseCase`, `ObserveLikedRecipeIdsUseCase`, `RecordRecipeViewedUseCase`, `ObserveRecentlyViewedUseCase`
- **User CRUD:** `CreateUserRecipeUseCase`, `UpdateUserRecipeUseCase`, `DeleteUserRecipeUseCase`, `ObserveUserRecipesUseCase`, `GetUserRecipeByIdUseCase`
- **Drafts:** `ObserveRecipeDraftUseCase`, `SaveRecipeDraftUseCase`, `ClearRecipeDraftUseCase`
- **Notifications:** `ObserveNotificationsUseCase`, `MarkNotificationReadUseCase`, `ClearNotificationsUseCase`, `SendRecipeReminderUseCase`

All `*UseCaseImpl` classes are `internal`; only interfaces and `RecipeReminderSender` are the public surface.

## Dependencies

**Project modules:** `:data-model`, `:data-network`, `:data-database`, `:data-preferences`, `:core-logging`

**Libraries:** kotlinx-coroutines, Koin, Ktor kotlinx JSON serialization

## Public API / usage

- `*UseCase` interfaces — injected into feature ViewModels
- `RecipeReminderSender` — bound in `:notification` to `FavoriteRecipeNotificationSender`
- `domainModule` — registered in `host/di/AppKoin.kt`

Consumed by `:host`, `:notification`, `:feature-main`, `:feature-all-recipes`, `:feature-recipe`, `:feature-create-recipe`.
