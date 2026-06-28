# :host

Application shell and Koin bootstrap.

## Purpose

Hosts the root Compose entry (`App()`), global theme/locale state, Navigation 3 root back stack, and aggregates all data/domain/feature DI modules. Provides platform hooks for deep links and notification clicks.

## Key classes / files

- `App.kt` — root composable; `rememberNavBackStack`, `NavDisplay`, wires all top-level `key.graph()` branches
- `AppViewModel.kt` — observes `AppStorage` for theme/locale; starts notification scheduler
- `contract/State.kt` — `locale: ThemeLocale`, `style: ThemeStyle`
- `di/AppKoin.kt` — `appModules` list + `expect fun initKoin(platformModule?)`
- `di/AppKoin.android.kt`, `di/AppKoin.jvm.kt` — platform Koin init with `AppLogger.init()`
- `di/DiHost.kt` — `hostModule` (`DeepLinkHandler`, `AppViewModel`)
- `DeepLinkHandler.kt`, `DeepLinkSetup.kt`, `DesktopDeepLinkSetup.kt`
- `NotificationSetup.kt` — `registerNotificationClickHandler()`

## Dependencies

**Project modules:** `:coreUi`, `:coreLogging`, `:navigation`, `:dataPreferences`, `:dataNetwork`, `:dataDatabase`, `:domain`, `:notification`, all `feature-*` modules

**Libraries:** Compose, AndroidX Lifecycle, Koin Compose, KMP Notifier Local

## Public API / usage

| Symbol | Role |
|--------|------|
| `App()` | Root composable — sole UI entry |
| `initKoin(platformModule?)` | Koin + `AppLogger` init |
| `handleDeepLink(uri)` | Routes URI to `DeepLinkHandler` |
| `registerNotificationClickHandler()` | Registers notification click listener |
| `registerDesktopDeepLinkHandlers(args)` | JVM-only desktop deep link setup |

Consumed only by `:androidApp` and `:desktopApp`.
