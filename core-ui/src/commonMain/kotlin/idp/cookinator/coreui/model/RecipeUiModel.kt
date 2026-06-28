package idp.cookinator.coreui.model

import idp.cookinator.model.Recipe

data class RecipeUiModel(
    val recipe: Recipe,
    val isSaved: Boolean,
) {
    companion object {
        val stub = RecipeUiModel(
            recipe = Recipe.stub,
            isSaved = true,
        )

        val stubs = Recipe.stubs.mapIndexed { index, recipe ->
            RecipeUiModel(
                recipe = recipe,
                isSaved = index % 2 == 0,
            )
        }
    }
}
