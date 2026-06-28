package idp.cookinator.feature.navigation.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.Scene
import androidx.navigationevent.NavigationEvent
import androidx.savedstate.serialization.SavedStateConfiguration
import idp.cookinator.coreui.utils.defaultTween
import idp.cookinator.feature.navigation.features.featureAllRecipesSerializer
import idp.cookinator.feature.navigation.features.featureMainSerializer
import idp.cookinator.feature.navigation.features.featureOnboardingSerializer
import idp.cookinator.feature.navigation.features.featureRecipeSerializer
import idp.cookinator.feature.navigation.features.featureSettingsSerializer
import idp.cookinator.feature.navigation.features.featureSplashSerializer
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

/**
 * A type alias for [NavBackStack] with [NavKey] as the key type, representing the navigation back
 * stack used in the app. This type alias simplifies the usage of [NavBackStack] throughout the
 * codebase by providing a more concise and descriptive name.
 */
typealias Navigator = NavBackStack<NavKey>

/**
 * The [SavedStateConfiguration] used for navigation, which includes serializers for all [NavKey]
 * types used in the app. This configuration is passed to the [rememberNavBackStack] function to
 * enable saving and restoring the navigation state across process death.
 */
val appConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        include(featureSplashSerializer)
        include(featureOnboardingSerializer)
        include(featureMainSerializer)
        include(featureAllRecipesSerializer)
        include(featureRecipeSerializer)
        include(featureSettingsSerializer)
    }
}

/**
 * Adds a [KSerializer] for the specified [NavKey] type to the [PolymorphicModuleBuilder].
 */
inline fun <reified T : NavKey> PolymorphicModuleBuilder<NavKey>.screen() {
    subclass(T::class, serializer())
}

/**
 * Navigates up in the [Navigator] by removing the last entry if there are more than one entry.
 * Returns the removed [NavKey] if navigation was successful, or null if there is only one entry left.
 */
fun Navigator.navigateUp(): NavKey? = when {
    size > 1 -> removeLastOrNull()
    else -> null
}

/**
 * Navigates to the specified [NavKey] by adding it to the end of the [Navigator].
 */
fun Navigator.replace(key: NavKey) {
    set(lastIndex, key)
}

/**
 * Navigates to the specified [NavKey] by adding it to the end of the [Navigator].
 * @param clearBackStack If true, clears the back stack before navigating.
 * @param navigateUp If true, navigates up before navigating to the new key.
 * @throws IllegalArgumentException if both [clearBackStack] and [navigateUp] are true,
 * as they cannot be performed simultaneously.
 */
fun Navigator.navigate(
    key: NavKey,
    clearBackStack: Boolean = false,
    navigateUp: Boolean = false,
) {
    assert(!(clearBackStack && navigateUp)) { "Cannot navigate up and clear back stack at the same time." }
    when {
        clearBackStack -> clear()
        navigateUp -> navigateUp()
    }
    add(key)
}

/**
 * Adds the specified [NavKey] to the end of the [Navigator] if it is not already present.
 * If the key is already present, it is moved to the end of the stack. This function ensures
 * that there are no duplicate entries in the back stack and that the most recently navigated
 * key is always at the end of the stack.
 */
fun Navigator.pushToTop(key: NavKey) {
    if (contains(key)) {
        remove(key)
    }
    add(key)
}

/**
 * Returns a [ContentTransform] for navigation transitions based on the direction of navigation.
 * If the boolean value is true, the transition will slide towards the start; if false, it will
 * slide towards the end. This function is used to create consistent animations for navigating
 * between screens in the app.
 */
fun <T : Any> navigationTransitionSpec(forward: Boolean): AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform {
    val towards = when {
        forward -> AnimatedContentTransitionScope.SlideDirection.Start
        else -> AnimatedContentTransitionScope.SlideDirection.End
    }
    return {
        slideIntoContainer(
            towards = towards,
            animationSpec = defaultTween()
        ) togetherWith slideOutOfContainer(
            towards = towards,
            animationSpec = defaultTween()
        )
    }
}

fun <T : Any> navigationPredictionTransitionSpec(): AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform {
    return { edge ->
        val towards = when (edge) {
            NavigationEvent.EDGE_RIGHT -> AnimatedContentTransitionScope.SlideDirection.End
            else -> AnimatedContentTransitionScope.SlideDirection.Start
        }
        scaleIn(
            initialScale = 0.9f,
            animationSpec = defaultTween(),
        ) + slideIntoContainer(
            towards = towards,
            animationSpec = defaultTween(),
        ) togetherWith scaleOut(
            targetScale = 0.9f,
            animationSpec = defaultTween(),
        ) + slideOutOfContainer(
            towards = towards,
            animationSpec = defaultTween(),
        )
    }
}