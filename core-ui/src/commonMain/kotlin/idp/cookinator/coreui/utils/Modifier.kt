package idp.cookinator.coreui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A pure Compose Multiplatform shadow modifier that perfectly follows custom Paths.
 */
fun Modifier.fauxCurveTopShadow(
    shape: Shape,
    shadowColor: Color = Color.Black.copy(alpha = 0.04f), // Low alpha since layers stack
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp, // Negative value pushes shadow up
) = this.drawBehind {
    val outline = shape.createOutline(size, layoutDirection, this)

    // Ensure the shape is path-based (which BottomBarCurveShape is)
    if (outline is Outline.Generic) {
        val path = outline.path

        // Shift the canvas up slightly to cast the shadow from the top edge
        translate(top = offsetY.toPx()) {

            // Draw overlapping strokes to simulate a Gaussian blur
            val steps = 5
            val stepWidth = blurRadius.toPx() / steps

            for (i in 1..steps) {
                drawPath(
                    path = path,
                    color = shadowColor,
                    style = Stroke(width = (steps - i + 1) * stepWidth)
                )
            }
        }
    }
}