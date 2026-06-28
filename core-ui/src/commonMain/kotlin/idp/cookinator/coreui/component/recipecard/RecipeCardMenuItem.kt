package idp.cookinator.coreui.component.recipecard

data class RecipeCardMenuItem(
    val label: String,
    val onClick: () -> Unit,
)
