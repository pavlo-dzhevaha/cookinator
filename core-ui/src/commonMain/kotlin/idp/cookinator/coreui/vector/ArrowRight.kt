package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.ArrowRight: ImageVector
    get() {
        if (_ArrowRight != null) {
            return _ArrowRight!!
        }
        _ArrowRight = ImageVector.Builder(
            name = "ArrowRight",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(12.891f, 5.228f)
                curveTo(12.607f, 5.531f, 12.608f, 6.022f, 12.893f, 6.324f)
                lineTo(17.512f, 11.225f)
                horizontalLineTo(4.727f)
                lineTo(4.629f, 11.232f)
                curveTo(4.274f, 11.283f, 4f, 11.608f, 4f, 12f)
                curveTo(4f, 12.428f, 4.326f, 12.775f, 4.727f, 12.775f)
                horizontalLineTo(17.51f)
                lineTo(12.893f, 17.676f)
                lineTo(12.822f, 17.763f)
                curveTo(12.61f, 18.066f, 12.633f, 18.496f, 12.891f, 18.772f)
                curveTo(13.174f, 19.075f, 13.635f, 19.076f, 13.919f, 18.774f)
                lineTo(19.769f, 12.567f)
                curveTo(19.905f, 12.431f, 19.992f, 12.241f, 19.999f, 12.029f)
                curveTo(20.007f, 11.82f, 19.935f, 11.609f, 19.786f, 11.45f)
                lineTo(13.919f, 5.226f)
                lineTo(13.837f, 5.151f)
                curveTo(13.552f, 4.926f, 13.148f, 4.952f, 12.891f, 5.228f)
                close()
            }
        }.build()

        return _ArrowRight!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowRight: ImageVector? = null
