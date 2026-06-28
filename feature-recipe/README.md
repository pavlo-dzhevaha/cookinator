# :feature-recipe

Recipe detail screen for discovery and user recipes.

## Purpose

Displays full recipe details — hero image, ingredients, instructions — with like/save and edit actions for user-owned recipes.

## Key classes / files

- `screen/detail/RecipeDetailScreen.kt`, `RecipeDetailContent.kt`
- `RecipeDetailViewModel.kt` — args via Koin `parametersOf`
- `RecipeDetailArgs.kt`
- `detail/contract/RecipeDetailState.kt`, `RecipeDetailIntent.kt`, `RecipeDetailEvent.kt`
- `detail/components/*` — hero, top bar, ingredient list, etc.
- `navigation/NavigationRecipe.kt` — `NavigationRecipe.graph()`
- `di/DiRecipe.kt` — `featureRecipeModule`

## Dependencies

**Project modules:** `:navigation`, `:localisation`, `:domain`, `:data-model`

**Libraries:** Compose resources, Coil, Koin (via `cookinator.feature`)

## Public API / usage

- **Owns routes:** `NavigationRecipe.Detail(recipeId: Int)`, `NavigationRecipe.UserDetail(userRecipeId: Long)`
- **Navigates to:** `NavigationCreateRecipe.Edit(userRecipeId)`
- `featureRecipeModule` — registered in `host/di/AppKoin.kt`
- `NavigationRecipe.graph()` — wired in `host/App.kt`
