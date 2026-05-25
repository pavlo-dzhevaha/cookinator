package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Star: ImageVector
    get() {
        if (_Star != null) {
            return _Star!!
        }
        _Star = ImageVector.Builder(
            name = "Star",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(17.325f, 14.197f)
                curveTo(17.092f, 14.435f, 16.985f, 14.779f, 17.038f, 15.116f)
                lineTo(17.838f, 19.777f)
                curveTo(17.906f, 20.172f, 17.747f, 20.572f, 17.433f, 20.8f)
                curveTo(17.125f, 21.037f, 16.716f, 21.065f, 16.38f, 20.876f)
                lineTo(12.395f, 18.687f)
                curveTo(12.256f, 18.61f, 12.102f, 18.568f, 11.945f, 18.563f)
                horizontalLineTo(11.701f)
                curveTo(11.616f, 18.576f, 11.533f, 18.605f, 11.458f, 18.649f)
                lineTo(7.471f, 20.847f)
                curveTo(7.274f, 20.951f, 7.051f, 20.988f, 6.832f, 20.951f)
                curveTo(6.3f, 20.845f, 5.944f, 20.311f, 6.032f, 19.747f)
                lineTo(6.832f, 15.087f)
                curveTo(6.886f, 14.747f, 6.778f, 14.401f, 6.545f, 14.159f)
                lineTo(3.296f, 10.844f)
                curveTo(3.024f, 10.566f, 2.93f, 10.149f, 3.054f, 9.773f)
                curveTo(3.174f, 9.398f, 3.482f, 9.124f, 3.854f, 9.063f)
                lineTo(8.326f, 8.38f)
                curveTo(8.666f, 8.343f, 8.965f, 8.125f, 9.118f, 7.803f)
                lineTo(11.089f, 3.549f)
                curveTo(11.136f, 3.455f, 11.196f, 3.368f, 11.269f, 3.294f)
                lineTo(11.35f, 3.227f)
                curveTo(11.392f, 3.178f, 11.441f, 3.137f, 11.495f, 3.104f)
                lineTo(11.593f, 3.066f)
                lineTo(11.746f, 3f)
                horizontalLineTo(12.125f)
                curveTo(12.463f, 3.037f, 12.761f, 3.25f, 12.917f, 3.568f)
                lineTo(14.913f, 7.803f)
                curveTo(15.057f, 8.113f, 15.337f, 8.328f, 15.66f, 8.38f)
                lineTo(20.133f, 9.063f)
                curveTo(20.511f, 9.12f, 20.827f, 9.394f, 20.952f, 9.773f)
                curveTo(21.069f, 10.153f, 20.968f, 10.57f, 20.691f, 10.844f)
                lineTo(17.325f, 14.197f)
                close()
            }
        }.build()

        return _Star!!
    }

fun Icons.getStartWithColor(color: Color): ImageVector = ImageVector.Builder(
    name = "Star_$color",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(fill = SolidColor(color)) {
        moveTo(17.325f, 14.197f)
        curveTo(17.092f, 14.435f, 16.985f, 14.779f, 17.038f, 15.116f)
        lineTo(17.838f, 19.777f)
        curveTo(17.906f, 20.172f, 17.747f, 20.572f, 17.433f, 20.8f)
        curveTo(17.125f, 21.037f, 16.716f, 21.065f, 16.38f, 20.876f)
        lineTo(12.395f, 18.687f)
        curveTo(12.256f, 18.61f, 12.102f, 18.568f, 11.945f, 18.563f)
        horizontalLineTo(11.701f)
        curveTo(11.616f, 18.576f, 11.533f, 18.605f, 11.458f, 18.649f)
        lineTo(7.471f, 20.847f)
        curveTo(7.274f, 20.951f, 7.051f, 20.988f, 6.832f, 20.951f)
        curveTo(6.3f, 20.845f, 5.944f, 20.311f, 6.032f, 19.747f)
        lineTo(6.832f, 15.087f)
        curveTo(6.886f, 14.747f, 6.778f, 14.401f, 6.545f, 14.159f)
        lineTo(3.296f, 10.844f)
        curveTo(3.024f, 10.566f, 2.93f, 10.149f, 3.054f, 9.773f)
        curveTo(3.174f, 9.398f, 3.482f, 9.124f, 3.854f, 9.063f)
        lineTo(8.326f, 8.38f)
        curveTo(8.666f, 8.343f, 8.965f, 8.125f, 9.118f, 7.803f)
        lineTo(11.089f, 3.549f)
        curveTo(11.136f, 3.455f, 11.196f, 3.368f, 11.269f, 3.294f)
        lineTo(11.35f, 3.227f)
        curveTo(11.392f, 3.178f, 11.441f, 3.137f, 11.495f, 3.104f)
        lineTo(11.593f, 3.066f)
        lineTo(11.746f, 3f)
        horizontalLineTo(12.125f)
        curveTo(12.463f, 3.037f, 12.761f, 3.25f, 12.917f, 3.568f)
        lineTo(14.913f, 7.803f)
        curveTo(15.057f, 8.113f, 15.337f, 8.328f, 15.66f, 8.38f)
        lineTo(20.133f, 9.063f)
        curveTo(20.511f, 9.12f, 20.827f, 9.394f, 20.952f, 9.773f)
        curveTo(21.069f, 10.153f, 20.968f, 10.57f, 20.691f, 10.844f)
        lineTo(17.325f, 14.197f)
        close()
    }
}.build()

@Suppress("ObjectPropertyName")
private var _Star: ImageVector? = null
