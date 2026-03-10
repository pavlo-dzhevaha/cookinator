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
