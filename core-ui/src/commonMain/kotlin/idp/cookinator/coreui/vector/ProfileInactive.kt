package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.ProfileInactive: ImageVector
    get() {
        if (_ProfileInactive != null) {
            return _ProfileInactive!!
        }
        _ProfileInactive = ImageVector.Builder(
            name = "ProfileInactive",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFC1C1C1)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(11.837f, 1.65f)
                curveTo(9.001f, 1.65f, 6.701f, 3.95f, 6.7f, 6.786f)
                lineTo(6.7f, 6.787f)
                horizontalLineTo(7.45f)
                lineTo(6.7f, 6.785f)
                lineTo(6.7f, 6.786f)
                curveTo(6.691f, 9.614f, 8.975f, 11.914f, 11.804f, 11.924f)
                lineTo(11.806f, 11.924f)
                horizontalLineTo(11.837f)
                curveTo(14.674f, 11.924f, 16.974f, 9.624f, 16.974f, 6.787f)
                curveTo(16.974f, 3.951f, 14.674f, 1.65f, 11.837f, 1.65f)
                close()
                moveTo(8.2f, 6.787f)
                curveTo(8.2f, 4.779f, 9.829f, 3.15f, 11.837f, 3.15f)
                curveTo(13.846f, 3.15f, 15.474f, 4.779f, 15.474f, 6.787f)
                curveTo(15.474f, 8.796f, 13.845f, 10.424f, 11.836f, 10.424f)
                horizontalLineTo(11.808f)
                curveTo(9.808f, 10.416f, 8.193f, 8.79f, 8.2f, 6.79f)
                verticalLineTo(6.787f)
                close()
                moveTo(5.75f, 18.787f)
                curveTo(5.75f, 17.989f, 6.305f, 17.084f, 7.442f, 16.338f)
                curveTo(8.558f, 15.606f, 10.124f, 15.112f, 11.844f, 15.112f)
                curveTo(13.556f, 15.112f, 15.123f, 15.601f, 16.242f, 16.33f)
                curveTo(17.382f, 17.072f, 17.939f, 17.972f, 17.939f, 18.766f)
                curveTo(17.939f, 19.151f, 17.815f, 19.438f, 17.592f, 19.679f)
                curveTo(17.352f, 19.937f, 16.968f, 20.174f, 16.411f, 20.368f)
                curveTo(15.288f, 20.761f, 13.682f, 20.912f, 11.844f, 20.912f)
                curveTo(10.015f, 20.912f, 8.409f, 20.767f, 7.282f, 20.378f)
                curveTo(6.724f, 20.185f, 6.338f, 19.951f, 6.098f, 19.694f)
                curveTo(5.874f, 19.455f, 5.75f, 19.171f, 5.75f, 18.787f)
                close()
                moveTo(11.844f, 13.612f)
                curveTo(9.853f, 13.612f, 7.997f, 14.18f, 6.619f, 15.085f)
                curveTo(5.261f, 15.976f, 4.25f, 17.283f, 4.25f, 18.787f)
                curveTo(4.25f, 19.553f, 4.52f, 20.203f, 5.003f, 20.719f)
                curveTo(5.469f, 21.217f, 6.102f, 21.558f, 6.793f, 21.796f)
                curveTo(8.166f, 22.27f, 9.982f, 22.412f, 11.844f, 22.412f)
                curveTo(13.718f, 22.412f, 15.535f, 22.264f, 16.906f, 21.784f)
                curveTo(17.597f, 21.543f, 18.228f, 21.199f, 18.692f, 20.699f)
                curveTo(19.171f, 20.181f, 19.439f, 19.531f, 19.439f, 18.766f)
                curveTo(19.439f, 17.26f, 18.42f, 15.957f, 17.06f, 15.073f)
                curveTo(15.681f, 14.175f, 13.825f, 13.612f, 11.844f, 13.612f)
                close()
            }
        }.build()

        return _ProfileInactive!!
    }

@Suppress("ObjectPropertyName")
private var _ProfileInactive: ImageVector? = null
