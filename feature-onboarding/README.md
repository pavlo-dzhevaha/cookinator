# :feature-onboarding

First-run welcome and onboarding flow.

## Purpose

Presents a welcome screen with language toggle. "Continue" marks onboarding complete and enters the main app.

## Key classes / files

- `screen/welcome/WelcomeScreen.kt`, `WelcomeContent.kt`
- `WelcomeScreenViewModel.kt`
- `welcome/contract/State.kt`, `Intent.kt`, `Event.kt`
- `navigation/NavigationOnboarding.kt` — `NavigationOnboarding.graph()`
- `di/DiOnboarding.kt` — `featureOnboardingModule`

## Dependencies

**Project modules:** `:navigation`, `:localisation`, `:data-preferences`

**Libraries:** Compose components.resources, Koin (via `cookinator.feature`)

## Public API / usage

- **Owns route:** `NavigationOnboarding.Welcome`
- **Navigates to:** `NavigationMain.Main`
- `featureOnboardingModule` — registered in `host/di/AppKoin.kt`
- `NavigationOnboarding.graph()` — wired in `host/App.kt`
