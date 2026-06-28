package idp.cookinator.domain.recipe

import idp.cookinator.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class RecipeDiscoveryStore {
    private val recipes = MutableStateFlow<List<Recipe>>(emptyList())

    fun observe(): Flow<List<Recipe>> = recipes.asStateFlow()

    fun get(): List<Recipe> = recipes.value

    fun set(recipes: List<Recipe>) {
        this.recipes.value = recipes
    }

    fun clear() {
        recipes.value = emptyList()
    }
}
