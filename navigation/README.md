# :navigation

Shared navigation contract for the app.

## Purpose

Defines serializable top-level `NavKey` routes, navigator helpers, saved-state configuration, and cross-cutting navigation utilities (deep links, pending recipe navigation). Screen graphs (`graph()` extensions) live in each `feature-*` module and are wired from `host/App.kt`.

## Key classes / files

- **NavKey routes:** `features/Splash.kt`, `Onboarding.kt`, `Main.kt`, `AllRecipes.kt`, `Recipe.kt`, `CreateRecipe.kt`, `Settings.kt`
- `features/AllRecipesFilter.kt` — `Trending`, `Category(dishType)`, `RecentlyViewed`
- `extension/NavigationExtensions.kt` — `Navigator` typealias, `appConfiguration`, `navigateUp()`, `replace()`, `navigate()`, transition specs
- `extension/SoloSceneStrategy.kt` — single-pane stack for main tabs
- `DeepLinkParser.kt` — parses `app://recipe?id=<int>`
- `PendingRecipeNavigation.kt` — deferred recipe-detail navigation after notifications/deep links

## Dependencies

**Project modules:** `:core-ui` (api — transitively exposes Compose, logging, localisation)

**Libraries:** Navigation 3, AndroidX Lifecycle viewmodel-navigation3, kotlinx-coroutines, kotlinx serialization

## Public API / usage

- `idp.cookinator.feature.navigation.features.*` — NavKey types and `AllRecipesFilter`
- `idp.cookinator.feature.navigation.extension.*` — `Navigator`, `appConfiguration`, navigation helpers
- `PendingRecipeNavigation` — implemented by `notification/NotificationDeepLinkStore`
- `DeepLinkParser` — used by `host/DeepLinkHandler`

Consumed by `:host`, `:notification`, and all `feature-*` modules.
