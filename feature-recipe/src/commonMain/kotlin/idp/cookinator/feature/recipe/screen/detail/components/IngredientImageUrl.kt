package idp.cookinator.feature.recipe.screen.detail.components

import idp.cookinator.model.Ingredient

internal fun Ingredient.imageUrl(): String? =
    image?.let { "https://img.spoonacular.com/ingredients_100x100/$it" }
