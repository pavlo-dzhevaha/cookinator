# :feature-splash

Launch splash screen and initial routing.

## Purpose

Shows a branded splash with a minimum 2s delay. Reads onboarding completion from preferences and routes to onboarding or the main app (no back stack).

## Key classes / files

- `SplashScreen.kt`, `SplashContent.kt`
- `SplashScreenViewModel.kt`
- `screen/contract/State.kt`, `Intent.kt`, `Event.kt`
- `navigation/NavigationSplash.kt` — `NavigationSplash.graph()`
- `di/DiSplash.kt` — `featureSplashModule`

## Dependencies

**Project modules:** `:navigation`, `:data-preferences`

**Libraries:** Compose, Koin (via `cookinator.feature` convention plugin)

## Public API / usage

- **Owns route:** `NavigationSplash.Splash`
- **Navigates to:** `NavigationMain.Main`, `NavigationOnboarding.Welcome`
- `featureSplashModule` — registered in `host/di/AppKoin.kt`
- `NavigationSplash.graph()` — wired in `host/App.kt`
