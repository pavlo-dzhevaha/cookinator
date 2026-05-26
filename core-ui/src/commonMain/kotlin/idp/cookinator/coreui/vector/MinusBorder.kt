package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.MinusBorder: ImageVector
    get() {
        if (_MinusBorder != null) {
            return _MinusBorder!!
        }
        _MinusBorder = ImageVector.Builder(
            name = "MinusBorder",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF130F26)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(3.05f, 12.037f)
                curveTo(3.05f, 8.427f, 3.661f, 6.26f, 4.96f, 4.961f)
                curveTo(6.259f, 3.661f, 8.427f, 3.05f, 12.037f, 3.05f)
                curveTo(15.646f, 3.05f, 17.814f, 3.661f, 19.113f, 4.961f)
                curveTo(20.412f, 6.26f, 21.024f, 8.427f, 21.024f, 12.037f)
                curveTo(21.024f, 15.646f, 20.412f, 17.814f, 19.113f, 19.113f)
                curveTo(17.814f, 20.412f, 15.646f, 21.024f, 12.037f, 21.024f)
                curveTo(8.427f, 21.024f, 6.259f, 20.412f, 4.96f, 19.113f)
                curveTo(3.661f, 17.814f, 3.05f, 15.646f, 3.05f, 12.037f)
                close()
                moveTo(12.037f, 1.55f)
                curveTo(8.344f, 1.55f, 5.643f, 2.156f, 3.9f, 3.9f)
                curveTo(2.156f, 5.644f, 1.55f, 8.344f, 1.55f, 12.037f)
                curveTo(1.55f, 15.729f, 2.156f, 18.43f, 3.9f, 20.174f)
                curveTo(5.643f, 21.918f, 8.344f, 22.524f, 12.037f, 22.524f)
                curveTo(15.729f, 22.524f, 18.43f, 21.918f, 20.174f, 20.174f)
                curveTo(21.917f, 18.43f, 22.524f, 15.729f, 22.524f, 12.037f)
                curveTo(22.524f, 8.344f, 21.917f, 5.644f, 20.174f, 3.9f)
                curveTo(18.43f, 2.156f, 15.729f, 1.55f, 12.037f, 1.55f)
                close()
                moveTo(8.459f, 11.287f)
                curveTo(8.044f, 11.287f, 7.709f, 11.623f, 7.709f, 12.037f)
                curveTo(7.709f, 12.451f, 8.044f, 12.787f, 8.459f, 12.787f)
                horizontalLineTo(15.615f)
                curveTo(16.029f, 12.787f, 16.365f, 12.451f, 16.365f, 12.037f)
                curveTo(16.365f, 11.623f, 16.029f, 11.287f, 15.615f, 11.287f)
                horizontalLineTo(8.459f)
                close()
            }
        }.build()

        return _MinusBorder!!
    }

@Suppress("ObjectPropertyName")
private var _MinusBorder: ImageVector? = null
