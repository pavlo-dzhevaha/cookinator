package idp.cookinator.coreui.component.appbottombar.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 * Custom curve for bottom bar, creating a dip in the center to accommodate a floating action button (FAB).
 *
 * @param ratio The depth of the curve, which should be a positive value. The actual depth in pixels
 * will be determined by multiplying this ratio with the height of the bottom bar.
 */
class BottomBarCurveShape(
    private val ratio: Float,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val center = width / 2f

            // The depth of the dip
            val depth = ratio

            // The opening at the top needs to be wider than the depth to allow for smooth shoulders
            val openingWidth = depth * 1.6f

            // Start at top-left
            moveTo(0f, 0f)

            // Draw line to the start of the left curve
            lineTo(center - openingWidth, 0f)

            // Left side of the cutout
            // Control points now progress logically towards the center to avoid the "pinch"
            cubicTo(
                x1 = center - openingWidth * 0.5f, y1 = 0f,    // Top left shoulder smooth transition
                x2 = center - depth * 0.8f, y2 = depth,        // Bottom left circular rounding
                x3 = center, y3 = depth                        // Bottom center of the dip
            )

            // Right side of the cutout
            cubicTo(
                x1 = center + depth * 0.8f, y1 = depth,        // Bottom right circular rounding
                x2 = center + openingWidth * 0.5f, y2 = 0f,    // Top right shoulder smooth transition
                x3 = center + openingWidth, y3 = 0f            // End of the curve
            )

            // Line to top-right
            lineTo(width, 0f)

            // Line to bottom-right
            lineTo(width, height)

            // Line to bottom-left
            lineTo(0f, height)

            // Close the path
            close()
        }
        return Outline.Generic(path)
    }
}
