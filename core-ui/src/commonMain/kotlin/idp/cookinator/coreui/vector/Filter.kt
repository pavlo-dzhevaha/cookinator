package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Filter: ImageVector
    get() {
        if (_Filter != null) {
            return _Filter!!
        }
        _Filter = ImageVector.Builder(
            name = "Filter",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF181818)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(7.179f, 4f)
                curveTo(6.206f, 4f, 5.34f, 4.154f, 4.746f, 4.748f)
                curveTo(4.153f, 5.342f, 4f, 6.211f, 4f, 7.186f)
                curveTo(4f, 8.161f, 4.153f, 9.03f, 4.747f, 9.624f)
                curveTo(5.34f, 10.219f, 6.207f, 10.372f, 7.179f, 10.372f)
                curveTo(8.151f, 10.372f, 9.018f, 10.219f, 9.611f, 9.624f)
                curveTo(10.204f, 9.03f, 10.357f, 8.161f, 10.357f, 7.186f)
                curveTo(10.357f, 6.211f, 10.204f, 5.343f, 9.611f, 4.748f)
                curveTo(9.018f, 4.154f, 8.151f, 4f, 7.179f, 4f)
                close()
                moveTo(5.373f, 7.186f)
                curveTo(5.373f, 6.288f, 5.531f, 5.908f, 5.717f, 5.722f)
                curveTo(5.903f, 5.535f, 6.283f, 5.377f, 7.179f, 5.377f)
                curveTo(8.075f, 5.377f, 8.454f, 5.535f, 8.64f, 5.722f)
                curveTo(8.826f, 5.908f, 8.984f, 6.288f, 8.984f, 7.186f)
                curveTo(8.984f, 8.085f, 8.826f, 8.465f, 8.64f, 8.651f)
                curveTo(8.454f, 8.837f, 8.075f, 8.996f, 7.179f, 8.996f)
                curveTo(6.282f, 8.996f, 5.903f, 8.837f, 5.717f, 8.651f)
                curveTo(5.532f, 8.465f, 5.373f, 8.085f, 5.373f, 7.186f)
                close()
                moveTo(12.702f, 7.187f)
                curveTo(12.702f, 6.807f, 13.009f, 6.499f, 13.389f, 6.499f)
                horizontalLineTo(19.313f)
                curveTo(19.692f, 6.499f, 20f, 6.807f, 20f, 7.187f)
                curveTo(20f, 7.567f, 19.692f, 7.875f, 19.313f, 7.875f)
                horizontalLineTo(13.389f)
                curveTo(13.009f, 7.875f, 12.702f, 7.567f, 12.702f, 7.187f)
                close()
                moveTo(16.822f, 13.628f)
                curveTo(15.85f, 13.628f, 14.983f, 13.781f, 14.39f, 14.375f)
                curveTo(13.797f, 14.97f, 13.644f, 15.839f, 13.644f, 16.814f)
                curveTo(13.644f, 17.789f, 13.797f, 18.658f, 14.39f, 19.252f)
                curveTo(14.983f, 19.846f, 15.85f, 20f, 16.822f, 20f)
                curveTo(17.794f, 20f, 18.661f, 19.846f, 19.254f, 19.252f)
                curveTo(19.847f, 18.658f, 20f, 17.789f, 20f, 16.814f)
                curveTo(20f, 15.839f, 19.847f, 14.97f, 19.254f, 14.375f)
                curveTo(18.661f, 13.781f, 17.794f, 13.628f, 16.822f, 13.628f)
                close()
                moveTo(15.017f, 16.814f)
                curveTo(15.017f, 15.915f, 15.175f, 15.535f, 15.361f, 15.349f)
                curveTo(15.547f, 15.163f, 15.926f, 15.004f, 16.822f, 15.004f)
                curveTo(17.718f, 15.004f, 18.097f, 15.163f, 18.283f, 15.349f)
                curveTo(18.469f, 15.535f, 18.627f, 15.915f, 18.627f, 16.814f)
                curveTo(18.627f, 17.712f, 18.469f, 18.092f, 18.283f, 18.279f)
                curveTo(18.097f, 18.465f, 17.718f, 18.623f, 16.822f, 18.623f)
                curveTo(15.926f, 18.623f, 15.547f, 18.465f, 15.361f, 18.279f)
                curveTo(15.175f, 18.092f, 15.017f, 17.712f, 15.017f, 16.814f)
                close()
                moveTo(4.687f, 16.125f)
                curveTo(4.307f, 16.125f, 4f, 16.433f, 4f, 16.813f)
                curveTo(4f, 17.193f, 4.307f, 17.501f, 4.687f, 17.501f)
                horizontalLineTo(10.612f)
                curveTo(10.991f, 17.501f, 11.298f, 17.193f, 11.298f, 16.813f)
                curveTo(11.298f, 16.433f, 10.991f, 16.125f, 10.612f, 16.125f)
                horizontalLineTo(4.687f)
                close()
            }
        }.build()

        return _Filter!!
    }

@Suppress("ObjectPropertyName")
private var _Filter: ImageVector? = null
