package idp.cookinator.coreui.extension

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.max

/**
 * Returns the longest side of the screen in Dp. This is useful for calculating sizes that should
 * be based on the screen size, such as the height of the bottom bar.
 */
@Composable
fun longestSide(): Dp {
    val (screenWidth, screenHeight) = LocalWindowInfo.current.containerDpSize
    return max(screenWidth, screenHeight)
}

/**
 * Checks if the keyboard is currently visible by checking the bottom inset of the IME. If the
 * bottom inset is greater than 0, it means that the keyboard is visible.
 */
@Composable
fun isKeyboardVisible(): Boolean = WindowInsets.ime.getBottom(LocalDensity.current) > 0

/**
 * This function removes focus from any focused component when the keyboard is hidden. It uses a
 * [LaunchedEffect] to listen for changes in the keyboard visibility and clears the focus when the
 * keyboard is hidden.
 */
@Composable
fun RemoveFocusWhenKeyboardHiddenEffect() {
    val isKeyboardVisible = isKeyboardVisible()
    val focusManager = LocalFocusManager.current
    LaunchedEffect(isKeyboardVisible) {
        if (!isKeyboardVisible) {
            focusManager.clearFocus()
        }
    }
}
