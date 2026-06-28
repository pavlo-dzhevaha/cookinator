# :desktopApp

Desktop JVM application shell for Cookinator.

## Purpose

Opens a native Compose Desktop window, bootstraps Koin and notifications, handles desktop deep links, then renders the shared `:host` `App()` composable.

## Key classes / files

- `main.kt` — `initKoin()`, KMPNotifier init, notification/deep-link setup, `application { Window { App() } }`
- Window: 1024×768 default, min 640×480; title from `:localisation`, icon from `:core-ui`

## Dependencies

**Project modules:** `:host`, `:localisation`, `:coreUi`

**Libraries:** Compose Desktop (`currentOs`), kotlinx-coroutines-swing, Compose resources, KMP Notifier Local

**Config:** `mainClass` `idp.cookinator.MainKt`; native distributions Dmg/Msi/Deb; macOS `bundleID` and `app://` URL scheme

## Public API / usage

Not consumed by other modules. Entry point for the Desktop app.

**Build / run:**

```shell
./gradlew :desktopApp:run
./gradlew :desktopApp:createDistributable
./gradlew :desktopApp:packageDistribution
```
