# Cookinator

Cookinator is a Kotlin Multiplatform (KMP) application designed for recipe management and discovery. It leverages Compose Multiplatform for a unified UI across Android and Desktop (JVM) platforms.

## Features

- **Multiplatform**: Shared business logic and UI across Android and Desktop.
- **Recipe Management**: Discovery, saving, and organizing recipes.
- **Onboarding**: A dedicated welcome flow for new users.
- **Internationalization**: Support for multiple languages (English, Ukrainian, etc.).
- **Theme Customization**: Support for different theme styles (Light, Dark, Dynamic).
- **Navigation**: Structured navigation across different app modules.

## Project Structure

The project is modularized to ensure separation of concerns and maintainability:

- **`:androidApp`**: Android-specific application module.
- **`:desktopApp`**: Desktop-specific application module.
- **`:host`**: The main entry point for the shared UI, hosting the root Composable and global ViewModels.
- **`:navigation`**: Navigation logic and route definitions using Jetpack Navigation (Compose Multiplatform).
- **`:core-ui`**: Shared UI components, styling, and icons.
- **`:localisation`**: Centralized string resources and multi-language support.
- **`:data-preferences`**: Persistent key-value storage using Multiplatform Settings and Datastore.
- **`:feature-splash`**: Entry point splash screen logic.
- **`:feature-onboarding`**: Onboarding and welcome screens.
- **`:feature-main`**: Main application features (Home, Saved recipes, Profile, Notifications).
- **`:feature-settings`**: User settings and preferences management.

## Tech Stack

- **Kotlin Multiplatform**: For sharing code across platforms.
- **Compose Multiplatform**: For building the UI for both Android and Desktop.
- **Koin**: Dependency injection framework.
- **Navigation Compose**: Type-safe navigation for Compose Multiplatform.
- **Multiplatform Settings**: Key-value storage for app preferences.
- **Kotlinx Coroutines**: For asynchronous programming.
- **Kotlinx Serialization**: For data serialization.

## Build and Run

### Android Application

To build and run the Android app, use the run configuration in Android Studio or run via terminal:

```shell
./gradlew :androidApp:assembleDebug
```

### Desktop (JVM) Application

To build and run the desktop app, use the run configuration in Android Studio or run via terminal:

```shell
./gradlew :desktopApp:run
```

---

*Cookinator is a demonstration of modern Kotlin Multiplatform development practices.*
