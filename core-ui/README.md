# :core-ui

Shared Compose Multiplatform UI kit.

## Purpose

Provides design tokens, reusable components, MVI ViewModel base classes, and UI models. Wraps theming, Coil image loading, and lifecycle-aware state collection for all feature screens.

## Key classes / files

- **Theme:** `AppTheme.kt`, `Theme.kt`, `ThemeStyle.kt`, `ThemeLocale.kt`, `ThemeColor.kt`, `ThemeSize.kt`, `ThemeTypography.kt`
- **MVI base:** `StateViewModel.kt`, `MviViewModel.kt`, `BaseState.kt`, `BaseIntent.kt`, `BaseEvent.kt`, `StateProvider.kt`
- **UI models:** `UiState.kt`, `RecipeUiModel.kt`
- **Components:** `AppTopBar`, `AppBottomBar`, `PrimaryButton`, `RecipeCard`, `PopularCategoryRecipeCard`, `RecentlyViewedRecipeCard`, `AdaptiveLazyVerticalGrid`, spacers
- **Vectors:** `vector/Icons.kt` — custom `ImageVector` icons
- **Utils:** `ImageLoader.kt`, `HtmlText.kt`, `Composable.kt`, `Modifier.kt`

## Dependencies

**Project modules:** `:localisation`, `:data-model`, `:core-logging` (api — re-exported to consumers)

**Libraries:** Compose (runtime, foundation, ui, material3), AndroidX Lifecycle, Koin Compose, Coil 3

## Public API / usage

- `AppTheme`, `Theme.color/size/typography`, `ThemeStyle`, `ThemeLocale` — theming in features and host
- `MviViewModel` / `StateViewModel` — base classes for feature ViewModels
- `UiState`, `RecipeUiModel` — shared UI state patterns
- Components and icons — imported by feature Content composables
- `AppLogger` — available transitively via `api(projects.coreLogging)`

Consumed directly by `:navigation` (api), `:host`, `:desktopApp`; transitively by all `feature-*` modules.
