# Cookinator

Cookinator is a Kotlin Multiplatform (KMP) application designed for recipe management and discovery. It leverages Compose Multiplatform for a unified UI across Android and Desktop (JVM) platforms.

## Features

- **Multiplatform**: Shared business logic and UI across Android and Desktop.
- **Recipe Management**: Discovery, saving, user-created recipes, and organizing favorites.
- **Onboarding**: A dedicated welcome flow for new users.
- **Internationalization**: Support for English and Ukrainian.
- **Theme Customization**: Light and dark theme styles.
- **Navigation**: Structured navigation across feature modules via Navigation 3.

## Project Structure

The project is modularized by layer. Each module has its own `README.md` with purpose, key classes, dependencies, and public API. Gradle convention plugins are documented in [`build-logic/README.md`](build-logic/README.md).

### Apps

| Module | Description |
|--------|-------------|
| [`:androidApp`](androidApp/README.md) | Android application shell (Activity, manifest, deep links). |
| [`:desktopApp`](desktopApp/README.md) | Desktop JVM application shell (Compose Desktop window). |

### Host

| Module | Description |
|--------|-------------|
| [`:host`](host/README.md) | Root `App()` composable, Koin bootstrap, global theme/locale state. |

### Navigation & UI

| Module | Description |
|--------|-------------|
| [`:navigation`](navigation/README.md) | Shared `NavKey` routes, navigator helpers, deep-link parsing. |
| [`:core-ui`](core-ui/README.md) | Theme tokens, MVI base classes, shared Compose components. |
| [`:localisation`](localisation/README.md) | Compose string resources (EN + UK) and `UiText`. |
| [`:core-logging`](core-logging/README.md) | `AppLogger` facade over Kermit. |

### Features

| Module | Description |
|--------|-------------|
| [`:feature-splash`](feature-splash/README.md) | Launch splash screen and initial routing. |
| [`:feature-onboarding`](feature-onboarding/README.md) | Welcome / first-run onboarding flow. |
| [`:feature-main`](feature-main/README.md) | Main shell with Home, Saved, Notifications, Profile tabs. |
| [`:feature-all-recipes`](feature-all-recipes/README.md) | Filtered recipe lists (trending, category, recently viewed). |
| [`:feature-recipe`](feature-recipe/README.md) | Recipe detail for discovery and user recipes. |
| [`:feature-create-recipe`](feature-create-recipe/README.md) | Create, edit, and fork user recipes. |
| [`:feature-settings`](feature-settings/README.md) | Theme and language preferences. |

### Domain

| Module | Description |
|--------|-------------|
| [`:domain`](domain/README.md) | Use cases for recipes, likes, drafts, and notifications. |

### Data

| Module | Description |
|--------|-------------|
| [`:data-model`](data-model/README.md) | Shared domain types (`Recipe`, `UserRecipe`, etc.). |
| [`:data-network`](data-network/README.md) | Spoonacular API and Supabase remote access. |
| [`:data-database`](data-database/README.md) | Room local persistence (recipes, likes, notifications). |
| [`:data-preferences`](data-preferences/README.md) | Key-value app settings via Multiplatform Settings. |

### Platform

| Module | Description |
|--------|-------------|
| [`:notification`](notification/README.md) | Local notifications and recipe reminder scheduling. |

## Tech Stack

- **Kotlin Multiplatform** — shared code across Android and Desktop JVM.
- **Compose Multiplatform** — unified UI toolkit.
- **Navigation 3** — type-safe navigation with serializable `NavKey` routes.
- **Koin** — dependency injection.
- **Room** — local SQLite persistence.
- **Ktor + Supabase** — HTTP client and server-side recipe cache.
- **Multiplatform Settings** — key-value preferences.
- **Coil 3** — image loading.
- **Kermit** (via `core-logging`) — structured logging.
- **KMP Notifier** — local push notifications.
- **Kotlinx Coroutines & Serialization** — async and JSON.

## Build and Run

### Android Application

```shell
./gradlew :androidApp:assembleDebug
```

### Desktop (JVM) Application

```shell
./gradlew :desktopApp:run
```

---

*Cookinator is a demonstration of modern Kotlin Multiplatform development practices.*
