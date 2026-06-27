package idp.cookinator.feature.navigation

import kotlinx.coroutines.flow.StateFlow

interface PendingRecipeNavigation {
    val pendingRecipeId: StateFlow<Int?>

    fun setRecipeDetail(recipeId: Int)

    fun consumeRecipeDetail()
}
