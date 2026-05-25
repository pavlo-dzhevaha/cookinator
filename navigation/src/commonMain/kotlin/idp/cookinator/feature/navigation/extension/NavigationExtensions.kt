package idp.cookinator.feature.navigation.extension

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.serializer

/**
 * Adds a [KSerializer] for the specified [NavKey] type to the [PolymorphicModuleBuilder].
 */
internal inline fun <reified T : NavKey> PolymorphicModuleBuilder<NavKey>.screen() {
    subclass(T::class, serializer())
}

/**
 * Navigates up in the [NavBackStack] by removing the last entry if there are more than one entry.
 * Returns the removed [NavKey] if navigation was successful, or null if there is only one entry left.
 */
fun NavBackStack<NavKey>.navigateUp(): NavKey? = when {
    size > 1 -> removeLastOrNull()
    else -> null
}

/**
 * Navigates to the specified [NavKey] by adding it to the end of the [NavBackStack].
 */
fun NavBackStack<NavKey>.replace(key: NavKey) {
    set(lastIndex, key)
}

/**
 * Navigates to the specified [NavKey] by adding it to the end of the [NavBackStack].
 * @param clearBackStack If true, clears the back stack before navigating.
 * @param navigateUp If true, navigates up before navigating to the new key.
 * @throws IllegalArgumentException if both [clearBackStack] and [navigateUp] are true,
 * as they cannot be performed simultaneously.
 */
fun NavBackStack<NavKey>.navigate(
    key: NavKey,
    clearBackStack: Boolean = false,
    navigateUp: Boolean = false,
) {
    assert(!(clearBackStack && navigateUp)) { "Cannot navigate up and clear back stack at the same time." }
    if (clearBackStack) {
        clear()
    }
    if (navigateUp) {
        navigateUp()
    }
    add(key)
}
