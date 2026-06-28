# :notification

Platform-specific local notifications and recipe reminders.

## Purpose

Schedules and delivers favorite-recipe reminder notifications. Persists notification records to Room, handles click → mark-read + deep-link navigation, and implements the domain `RecipeReminderSender` port.

## Key classes / files

- `di/DiNotification.kt` — expect `notificationModule` (+ Android/JVM actuals)
- `NotificationScheduler` — interface; `AndroidNotificationScheduler`, `DesktopNotificationScheduler`
- `FavoriteRecipeNotificationSender` — implements `RecipeReminderSender`
- `NotificationClickHandler`, `NotificationDeepLinkStore` — implements `PendingRecipeNavigation`
- `NotificationPayloadKeys`, `NotificationPayloadParser.kt`
- `FavoriteRecipeReminderWorker.kt` — Android WorkManager periodic job (2h interval)

## Dependencies

**Project modules:** `:navigation`, `:data-database`, `:data-model`, `:domain`, `:localisation`

**Libraries:** Koin, KMP Notifier Local, Compose resources; AndroidX WorkManager (Android only)

## Public API / usage

- `notificationModule` — registered in `host/di/AppKoin.kt`; binds `RecipeReminderSender`, `PendingRecipeNavigation`, schedulers
- `NotificationScheduler.startPeriodicReminders()` — called from `AppViewModel` on startup
- `NotificationClickHandler.register()` — called via `host/NotificationSetup.kt`
- `SendRecipeReminderUseCase` (domain) — manual reminder from Notifications screen

Consumed only by `:host`.
