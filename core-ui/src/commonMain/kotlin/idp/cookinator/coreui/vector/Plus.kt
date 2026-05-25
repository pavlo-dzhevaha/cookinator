package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Plus: ImageVector
    get() {
        if (_Plus != null) {
            return _Plus!!
        }
        _Plus = ImageVector.Builder(
            name = "Plus",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(13.387f, 5.388f)
                curveTo(13.387f, 4.621f, 12.766f, 4f, 12f, 4f)
                curveTo(11.234f, 4f, 10.614f, 4.621f, 10.614f, 5.388f)
                verticalLineTo(10.612f)
                horizontalLineTo(5.386f)
                curveTo(4.621f, 10.612f, 4f, 11.234f, 4f, 12f)
                curveTo(4f, 12.766f, 4.621f, 13.387f, 5.386f, 13.387f)
                horizontalLineTo(10.614f)
                verticalLineTo(18.612f)
                curveTo(10.614f, 19.379f, 11.234f, 20f, 12f, 20f)
                curveTo(12.766f, 20f, 13.387f, 19.379f, 13.387f, 18.612f)
                verticalLineTo(13.387f)
                horizontalLineTo(18.614f)
                curveTo(19.379f, 13.387f, 20f, 12.766f, 20f, 12f)
                curveTo(20f, 11.234f, 19.379f, 10.612f, 18.614f, 10.612f)
                horizontalLineTo(13.387f)
                verticalLineTo(5.388f)
                close()
            }
        }.build()

        return _Plus!!
    }

@Suppress("ObjectPropertyName")
private var _Plus: ImageVector? = null
