package idp.cookinator.coreui.utils

import androidx.compose.animation.core.tween

/**
 * Default duration for tween animations in milliseconds.
 */
private const val defaultDurationMillis = 300

/**
 * A helper function to create a default tween animation spec with a specified duration.
 *
 * @param durationMillis The duration of the tween animation in milliseconds. Default is [defaultDurationMillis]
 */
fun <T> defaultTween(durationMillis: Int = defaultDurationMillis) = tween<T>(durationMillis)