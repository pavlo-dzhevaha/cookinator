package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Play: ImageVector
    get() {
        if (_Play != null) {
            return _Play!!
        }
        _Play = ImageVector.Builder(
            name = "Play",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color(0xFF303030))) {
                moveTo(6.439f, 3.328f)
                curveTo(6.865f, 3.109f, 7.313f, 3f, 7.781f, 3f)
                curveTo(8.271f, 3.022f, 9.188f, 3.352f, 9.55f, 3.503f)
                curveTo(11.936f, 4.465f, 16.496f, 7.615f, 18.243f, 9.539f)
                curveTo(18.541f, 9.846f, 18.861f, 10.217f, 18.946f, 10.305f)
                curveTo(19.308f, 10.786f, 19.5f, 11.377f, 19.5f, 12.013f)
                curveTo(19.5f, 12.58f, 19.33f, 13.148f, 18.989f, 13.608f)
                curveTo(18.925f, 13.695f, 18.626f, 14.067f, 18.392f, 14.307f)
                lineTo(18.264f, 14.439f)
                curveTo(16.474f, 16.429f, 12.021f, 19.425f, 9.763f, 20.388f)
                curveTo(9.763f, 20.41f, 8.42f, 20.978f, 7.781f, 21f)
                horizontalLineTo(7.696f)
                curveTo(6.716f, 21f, 5.8f, 20.431f, 5.331f, 19.513f)
                curveTo(5.075f, 19.01f, 4.841f, 17.544f, 4.82f, 17.522f)
                curveTo(4.628f, 16.21f, 4.5f, 14.2f, 4.5f, 11.989f)
                curveTo(4.5f, 9.671f, 4.628f, 7.571f, 4.862f, 6.281f)
                curveTo(4.862f, 6.259f, 5.097f, 5.078f, 5.246f, 4.684f)
                curveTo(5.48f, 4.115f, 5.906f, 3.634f, 6.439f, 3.328f)
                close()
            }
        }.build()

        return _Play!!
    }

@Suppress("ObjectPropertyName")
private var _Play: ImageVector? = null
