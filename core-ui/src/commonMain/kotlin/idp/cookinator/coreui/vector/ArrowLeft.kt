package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.ArrowLeft: ImageVector
    get() {
        if (_ArrowLeft != null) {
            return _ArrowLeft!!
        }
        _ArrowLeft = ImageVector.Builder(
            name = "ArrowLeft",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(11.109f, 5.228f)
                curveTo(11.393f, 5.531f, 11.392f, 6.022f, 11.107f, 6.324f)
                lineTo(6.488f, 11.225f)
                horizontalLineTo(19.273f)
                lineTo(19.371f, 11.232f)
                curveTo(19.726f, 11.283f, 20f, 11.608f, 20f, 12f)
                curveTo(20f, 12.428f, 19.674f, 12.775f, 19.273f, 12.775f)
                horizontalLineTo(6.489f)
                lineTo(11.107f, 17.676f)
                lineTo(11.178f, 17.763f)
                curveTo(11.39f, 18.066f, 11.367f, 18.496f, 11.109f, 18.772f)
                curveTo(10.826f, 19.075f, 10.365f, 19.076f, 10.081f, 18.774f)
                lineTo(4.231f, 12.567f)
                curveTo(4.095f, 12.431f, 4.008f, 12.241f, 4.001f, 12.029f)
                curveTo(3.993f, 11.82f, 4.065f, 11.609f, 4.214f, 11.45f)
                lineTo(10.081f, 5.226f)
                lineTo(10.163f, 5.151f)
                curveTo(10.448f, 4.926f, 10.852f, 4.952f, 11.109f, 5.228f)
                close()
            }
        }.build()

        return _ArrowLeft!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowLeft: ImageVector? = null
