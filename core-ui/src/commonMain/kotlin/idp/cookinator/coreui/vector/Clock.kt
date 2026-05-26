package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Clock: ImageVector
    get() {
        if (_Clock != null) {
            return _Clock!!
        }
        _Clock = ImageVector.Builder(
            name = "Clock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(12f, 3f)
                curveTo(16.977f, 3f, 21f, 7.032f, 21f, 12f)
                curveTo(21f, 16.977f, 16.977f, 21f, 12f, 21f)
                curveTo(7.032f, 21f, 3f, 16.977f, 3f, 12f)
                curveTo(3f, 7.032f, 7.032f, 3f, 12f, 3f)
                close()
                moveTo(11.685f, 7.437f)
                curveTo(11.316f, 7.437f, 11.01f, 7.734f, 11.01f, 8.112f)
                verticalLineTo(12.657f)
                curveTo(11.01f, 12.891f, 11.136f, 13.107f, 11.343f, 13.233f)
                lineTo(14.871f, 15.339f)
                curveTo(14.979f, 15.402f, 15.096f, 15.438f, 15.222f, 15.438f)
                curveTo(15.447f, 15.438f, 15.672f, 15.321f, 15.798f, 15.105f)
                curveTo(15.987f, 14.79f, 15.888f, 14.376f, 15.564f, 14.178f)
                lineTo(12.36f, 12.27f)
                verticalLineTo(8.112f)
                curveTo(12.36f, 7.734f, 12.054f, 7.437f, 11.685f, 7.437f)
                close()
            }
        }.build()

        return _Clock!!
    }

@Suppress("ObjectPropertyName")
private var _Clock: ImageVector? = null
