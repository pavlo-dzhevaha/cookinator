# :feature-all-recipes

Filtered recipe list screens.

## Purpose

Shows recipe lists filtered by trending, dish type category, or recently viewed. Supports sync, like/unlike, and navigation to detail or "create from recipe".

## Key classes / files

- `screen/AllRecipesScreen.kt`, `AllRecipesContent.kt`
- `AllRecipesViewModel.kt` — filter via Koin `parametersOf`
- `contract/AllRecipesState.kt`, `AllRecipesIntent.kt`, `AllRecipesEvent.kt`
- `navigation/NavigationAllRecipes.kt` — `NavigationAllRecipes.graph()`
- `di/DiAllRecipes.kt` — `featureAllRecipesModule`

## Dependencies

**Project modules:** `:navigation`, `:localisation`, `:domain`, `:data-model`

**Libraries:** Compose resources, Koin (via `cookinator.feature`)

## Public API / usage

- **Owns route:** `NavigationAllRecipes.All(filter: AllRecipesFilter)` — `Trending`, `Category(dishType)`, `RecentlyViewed`
- **Navigates to:** `NavigationRecipe.Detail`, `NavigationCreateRecipe.CreateFromRecipe`
- `featureAllRecipesModule` — registered in `host/di/AppKoin.kt`
- `NavigationAllRecipes.graph()` — wired in `host/App.kt`
