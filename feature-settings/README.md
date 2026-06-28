# :feature-settings

App preferences screen.

## Purpose

Lets users change theme style (light/dark) and locale (EN/UK). Persists choices via `AppStorage`.

## Key classes / files

- `screen/SettingsScreen.kt`, `SettingsContent.kt`
- `SettingsScreenViewModel.kt`
- `contract/State.kt`, `Intent.kt`, `Event.kt`, `Action.kt`
- `navigation/NavigationSettings.kt` — `NavigationSettings.graph()`
- `di/DiSettings.kt` — `featureSettingsModule`

## Dependencies

**Project modules:** `:navigation`, `:localisation`, `:data-preferences`

**Libraries:** Compose components.resources, Koin (via `cookinator.feature`)

## Public API / usage

- **Owns route:** `NavigationSettings.Settings`
- **Navigates to:** none (popped via host `navigateUp()`)
- `featureSettingsModule` — registered in `host/di/AppKoin.kt`
- `NavigationSettings.graph()` — wired in `host/App.kt`
