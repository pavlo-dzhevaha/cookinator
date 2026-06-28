# :androidApp

Android application shell for Cookinator.

## Purpose

Owns platform lifecycle (Application/Activity), Android manifest, permissions, deep links, and notifications. Bootstraps Koin then delegates all UI and navigation to the shared `:host` composable `App()`.

## Key classes / files

- `MainApplication.kt` — `onCreate()` → `initKoin()` with Android `Context` module
- `MainActivity.kt` — KMPNotifier init, notification click handler, deep-link `Intent`, `setContent { App() }`
- `AndroidManifest.xml` — permissions, `FileProvider`, deep link `app://recipe`, predictive back

## Dependencies

**Project modules:** `:host` (all other modules are transitive)

**Libraries:** AndroidX Activity Compose, Compose UI tooling preview, Koin (compose + android), KMP Notifier Local

**Config:** `applicationId` `idp.cookinator`, `compileSdk` 37, `minSdk` 24, release R8 minify enabled

## Public API / usage

Not consumed by other modules. Entry point for the Android app.

**Build / run:**

```shell
./gradlew :androidApp:assembleDebug
./gradlew :androidApp:installDebug
```
