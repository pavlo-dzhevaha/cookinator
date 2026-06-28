# :localisation

Centralized internationalization for the app.

## Purpose

Provides Compose Multiplatform string resources and a small `UiText` abstraction for dynamic vs. resource-backed text in shared UI components.

## Key classes / files

- `UiText.kt` — sealed interface with `Dynamic` / `Resource` variants
- `src/commonMain/composeResources/` — split `strings-*` XML files by domain
- `values/` — English (default)
- `values-uk/` — Ukrainian

## Dependencies

**Project modules:** none

**Libraries:** Compose runtime, Compose components.resources

## Public API / usage

- Generated `cookinator.localisation.generated.resources.Res` and string accessors — primary consumption pattern in features, `core-ui`, `notification`, `desktopApp`
- `UiText` — used by `core-ui` components (`PrimaryButton`, `RadioView`, theme pickers) for localized or runtime text

Languages: English + Ukrainian. Features use `generateResClass = never`; this module owns the public `Res` class (`publicResClass = true`).

Consumed by `:core-ui`, all `feature-*` modules, `:notification`, `:desktopApp`.
