package idp.cookinator.feature.recipe.screen.detail

internal data class RecipeDetailArgs(
    val recipeId: Int? = null,
    val userRecipeId: Long? = null,
) {
    init {
        require((recipeId != null) xor (userRecipeId != null)) {
            "Exactly one of recipeId or userRecipeId must be set"
        }
    }
}
