# :feature-create-recipe

Create, edit, and fork user recipes.

## Purpose

Multi-step form for user recipe creation with draft persistence, image picker, dish type/ingredient pickers, and diet toggles. Supports create, edit, and fork-from-discovery modes.

## Key classes / files

- `screen/create/CreateRecipeScreen.kt`, `CreateRecipeContent.kt`
- `CreateRecipeViewModel.kt` — modes: `CreateRecipeMode` (Create / Edit / FromRecipe)
- `create/contract/CreateRecipeState.kt`, `CreateRecipeIntent.kt`, `CreateRecipeEvent.kt`, `DietType`, `IngredientFormItem`, `PickerType`
- `create/components/*` — meta card, diet toggles, picker sheet
- `image/RecipeImagePicker.*` — expect/actual platform image picker
- `navigation/NavigationCreateRecipe.kt` — `NavigationCreateRecipe.graph()`
- `di/DiCreateRecipe.kt` — `featureCreateRecipeModule`

## Dependencies

**Project modules:** `:navigation`, `:localisation`, `:domain`, `:data-model`

**Libraries:** Compose resources, Coil; AndroidX Activity Compose (Android only)

## Public API / usage

- **Owns routes:** `NavigationCreateRecipe.Create`, `NavigationCreateRecipe.Edit(userRecipeId)`, `NavigationCreateRecipe.CreateFromRecipe(recipeId)`
- **Navigates to:** back via `navigateUp()` only
- `featureCreateRecipeModule` — registered in `host/di/AppKoin.kt`
- `NavigationCreateRecipe.graph()` — wired in `host/App.kt`
