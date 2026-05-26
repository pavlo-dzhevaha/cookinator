package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Location: ImageVector
    get() {
        if (_Location != null) {
            return _Location!!
        }
        _Location = ImageVector.Builder(
            name = "Location",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(14.51f, 10.71f)
                curveTo(14.51f, 9.329f, 13.391f, 8.21f, 12.01f, 8.21f)
                curveTo(10.629f, 8.21f, 9.51f, 9.329f, 9.51f, 10.71f)
                curveTo(9.51f, 12.091f, 10.629f, 13.21f, 12.01f, 13.21f)
                curveTo(13.391f, 13.21f, 14.51f, 12.091f, 14.51f, 10.71f)
                close()
                moveTo(12f, 21f)
                curveTo(9.101f, 21f, 4.5f, 15.959f, 4.5f, 10.599f)
                curveTo(4.5f, 6.402f, 7.857f, 3f, 12f, 3f)
                curveTo(16.142f, 3f, 19.5f, 6.402f, 19.5f, 10.599f)
                curveTo(19.5f, 15.959f, 14.899f, 21f, 12f, 21f)
                close()
            }
        }.build()

        return _Location!!
    }

@Suppress("ObjectPropertyName")
private var _Location: ImageVector? = null
