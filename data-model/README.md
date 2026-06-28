# :data-model

Shared platform-agnostic domain types for the Cookinator app.

## Purpose

Provides `@Serializable` data classes used across network, database, domain, and UI layers. Pure types module with no business logic or platform code.

## Key classes / files

- `Recipe.kt` — `Recipe`, `Ingredient`, `Instruction`, `InstructionStep`
- `UserRecipe.kt` — `UserRecipe` with `toRecipe()` mapper
- `RecipeDraft.kt` — `RecipeDraft`, `RecipeDraftIngredient` (create-recipe flow)
- `AppNotification.kt` — in-app notification model (assembled from DB)

## Dependencies

**Project modules:** none

**Libraries:** Ktor kotlinx JSON serialization (`@Serializable`)

## Public API / usage

All types in `idp.cookinator.model` are public and consumed by `:domain`, `:data-network`, `:data-database`, `:core-ui`, `:notification`, and several feature modules. Features should prefer `:domain` use cases over direct data access, but UI layers import these types for display and previews.
