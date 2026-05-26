package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Recipe: ImageVector
    get() {
        if (_Recipe != null) {
            return _Recipe!!
        }
        _Recipe = ImageVector.Builder(
            name = "Recipe",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(8.926f, 16.39f)
                horizontalLineTo(14.312f)
                curveTo(14.718f, 16.39f, 15.054f, 16.05f, 15.054f, 15.64f)
                curveTo(15.054f, 15.23f, 14.718f, 14.9f, 14.312f, 14.9f)
                horizontalLineTo(8.926f)
                curveTo(8.52f, 14.9f, 8.183f, 15.23f, 8.183f, 15.64f)
                curveTo(8.183f, 16.05f, 8.52f, 16.39f, 8.926f, 16.39f)
                close()
                moveTo(12.272f, 9.9f)
                horizontalLineTo(8.926f)
                curveTo(8.52f, 9.9f, 8.183f, 10.24f, 8.183f, 10.65f)
                curveTo(8.183f, 11.06f, 8.52f, 11.39f, 8.926f, 11.39f)
                horizontalLineTo(12.272f)
                curveTo(12.678f, 11.39f, 13.015f, 11.06f, 13.015f, 10.65f)
                curveTo(13.015f, 10.24f, 12.678f, 9.9f, 12.272f, 9.9f)
                close()
                moveTo(19.338f, 9.026f)
                curveTo(19.571f, 9.023f, 19.824f, 9.02f, 20.055f, 9.02f)
                curveTo(20.302f, 9.02f, 20.5f, 9.22f, 20.5f, 9.47f)
                verticalLineTo(17.51f)
                curveTo(20.5f, 19.99f, 18.51f, 22f, 16.055f, 22f)
                horizontalLineTo(8.173f)
                curveTo(5.599f, 22f, 3.5f, 19.89f, 3.5f, 17.29f)
                verticalLineTo(6.51f)
                curveTo(3.5f, 4.03f, 5.5f, 2f, 7.965f, 2f)
                horizontalLineTo(13.252f)
                curveTo(13.51f, 2f, 13.708f, 2.21f, 13.708f, 2.46f)
                verticalLineTo(5.68f)
                curveTo(13.708f, 7.51f, 15.203f, 9.01f, 17.015f, 9.02f)
                curveTo(17.438f, 9.02f, 17.811f, 9.023f, 18.138f, 9.026f)
                curveTo(18.392f, 9.028f, 18.618f, 9.03f, 18.817f, 9.03f)
                curveTo(18.958f, 9.03f, 19.14f, 9.028f, 19.338f, 9.026f)
                close()
                moveTo(19.611f, 7.566f)
                curveTo(18.797f, 7.569f, 17.838f, 7.566f, 17.148f, 7.559f)
                curveTo(16.053f, 7.559f, 15.151f, 6.648f, 15.151f, 5.542f)
                verticalLineTo(2.906f)
                curveTo(15.151f, 2.475f, 15.668f, 2.261f, 15.965f, 2.572f)
                curveTo(16.5f, 3.135f, 17.237f, 3.908f, 17.97f, 4.678f)
                curveTo(18.701f, 5.446f, 19.429f, 6.211f, 19.951f, 6.759f)
                curveTo(20.24f, 7.062f, 20.028f, 7.565f, 19.611f, 7.566f)
                close()
            }
        }.build()

        return _Recipe!!
    }

@Suppress("ObjectPropertyName")
private var _Recipe: ImageVector? = null
