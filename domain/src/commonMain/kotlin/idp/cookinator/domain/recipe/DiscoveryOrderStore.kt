package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import idp.cookinator.preferences.AppStorage
import kotlinx.coroutines.flow.Flow

internal class DiscoveryOrderStore(
    private val appStorage: AppStorage,
) {
    suspend fun getOrder(): List<Int>? = appStorage.getDiscoveryRecipeOrder()

    suspend fun setOrder(ids: List<Int>) {
        appStorage.setDiscoveryRecipeOrder(ids)
    }

    suspend fun clearOrder() {
        appStorage.setDiscoveryRecipeOrder(null)
    }

    fun observeOrder(): Flow<List<Int>> = appStorage.observeDiscoveryRecipeOrder()
}

internal fun applyDiscoveryOrder(
    recipes: List<Recipe>,
    order: List<Int>,
): List<Recipe> {
    if (order.isEmpty()) return recipes
    val byId = recipes.associateBy { it.id }
    val ordered = order.mapNotNull { byId[it] }
    val orderSet = order.toSet()
    val remaining = recipes.filter { recipe -> recipe.id !in orderSet }
    return ordered + remaining
}
