package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.More: ImageVector
    get() {
        if (_More != null) {
            return _More!!
        }
        _More = ImageVector.Builder(
            name = "More",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(24f)
                    horizontalLineToRelative(-24f)
                    close()
                }
            ) {
                path(
                    fill = SolidColor(Color(0xFF303030)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(5f, 10f)
                    curveTo(3.895f, 10f, 3f, 10.895f, 3f, 12f)
                    curveTo(3f, 13.105f, 3.895f, 14f, 5f, 14f)
                    curveTo(6.105f, 14f, 7f, 13.105f, 7f, 12f)
                    curveTo(7f, 10.895f, 6.105f, 10f, 5f, 10f)
                    close()
                    moveTo(10f, 12f)
                    curveTo(10f, 10.895f, 10.895f, 10f, 12f, 10f)
                    curveTo(13.105f, 10f, 14f, 10.895f, 14f, 12f)
                    curveTo(14f, 13.105f, 13.105f, 14f, 12f, 14f)
                    curveTo(10.895f, 14f, 10f, 13.105f, 10f, 12f)
                    close()
                    moveTo(17f, 12f)
                    curveTo(17f, 10.895f, 17.895f, 10f, 19f, 10f)
                    curveTo(20.105f, 10f, 21f, 10.895f, 21f, 12f)
                    curveTo(21f, 13.105f, 20.105f, 14f, 19f, 14f)
                    curveTo(17.895f, 14f, 17f, 13.105f, 17f, 12f)
                    close()
                }
            }
        }.build()

        return _More!!
    }

@Suppress("ObjectPropertyName")
private var _More: ImageVector? = null
