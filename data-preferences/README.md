# :data-preferences

Typed key-value app settings.

## Purpose

Wraps [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings) with a typed API for onboarding state, theme, locale, recipe draft, and discovery shuffle order.

## Key classes / files

- `AppStorage.kt` — typed settings API over `FlowSettings`
- `di/DiStorage.kt` — expect `dataPreferencesModule`
- `di/DiStorage.android.kt` — `SharedPreferencesSettings` (Android)
- `di/DiStorage.jvm.kt` — `PreferencesSettings` (Desktop)

## Dependencies

**Project modules:** none

**Libraries:** kotlinx-coroutines, Multiplatform Settings (+ coroutines), Koin

## Public API / usage

- `AppStorage` — methods for onboarding, theme, language, recipe draft JSON, discovery order; consumed by `:domain`, `:host`, `:feature-splash`, `:feature-onboarding`, `:feature-settings`
- `dataPreferencesModule` — registered in `host/di/AppKoin.kt`
