package idp.cookinator.feature.createrecipe.screen.create

internal sealed class CreateRecipeMode {
    data object Create : CreateRecipeMode()

    data class Edit(val userRecipeId: Long) : CreateRecipeMode()

    data class FromRecipe(val recipeId: Int) : CreateRecipeMode()
}
