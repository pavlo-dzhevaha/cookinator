package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Bookmark: ImageVector
    get() {
        if (_Bookmark != null) {
            return _Bookmark!!
        }
        _Bookmark = ImageVector.Builder(
            name = "Bookmark",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(11.97f, 2.5f)
                curveTo(5.583f, 2.5f, 4.504f, 3.432f, 4.504f, 10.929f)
                curveTo(4.504f, 19.322f, 4.347f, 21.5f, 5.943f, 21.5f)
                curveTo(7.538f, 21.5f, 10.143f, 17.816f, 11.97f, 17.816f)
                curveTo(13.797f, 17.816f, 16.402f, 21.5f, 17.997f, 21.5f)
                curveTo(19.593f, 21.5f, 19.436f, 19.322f, 19.436f, 10.929f)
                curveTo(19.436f, 3.432f, 18.357f, 2.5f, 11.97f, 2.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(7.959f, 2.028f)
                curveTo(9.038f, 1.807f, 10.369f, 1.75f, 11.97f, 1.75f)
                curveTo(13.572f, 1.75f, 14.902f, 1.807f, 15.982f, 2.028f)
                curveTo(17.075f, 2.252f, 17.976f, 2.656f, 18.652f, 3.397f)
                curveTo(19.321f, 4.13f, 19.693f, 5.107f, 19.907f, 6.319f)
                curveTo(20.119f, 7.529f, 20.186f, 9.047f, 20.186f, 10.929f)
                curveTo(20.186f, 11.655f, 20.188f, 12.337f, 20.189f, 12.977f)
                curveTo(20.194f, 16.001f, 20.198f, 18.081f, 20.062f, 19.437f)
                curveTo(19.98f, 20.246f, 19.84f, 20.933f, 19.531f, 21.426f)
                curveTo(19.365f, 21.691f, 19.145f, 21.911f, 18.859f, 22.058f)
                curveTo(18.578f, 22.201f, 18.281f, 22.25f, 17.997f, 22.25f)
                curveTo(17.349f, 22.25f, 16.719f, 21.893f, 16.212f, 21.54f)
                curveTo(15.716f, 21.196f, 15.194f, 20.75f, 14.701f, 20.328f)
                curveTo(14.662f, 20.295f, 14.623f, 20.261f, 14.584f, 20.228f)
                curveTo(14.039f, 19.763f, 13.527f, 19.334f, 13.047f, 19.019f)
                curveTo(12.549f, 18.691f, 12.198f, 18.566f, 11.97f, 18.566f)
                curveTo(11.742f, 18.566f, 11.392f, 18.691f, 10.894f, 19.019f)
                curveTo(10.413f, 19.334f, 9.902f, 19.763f, 9.357f, 20.228f)
                curveTo(9.318f, 20.261f, 9.279f, 20.295f, 9.24f, 20.328f)
                curveTo(8.747f, 20.75f, 8.225f, 21.196f, 7.729f, 21.54f)
                curveTo(7.221f, 21.893f, 6.592f, 22.25f, 5.943f, 22.25f)
                curveTo(5.659f, 22.25f, 5.363f, 22.201f, 5.082f, 22.058f)
                curveTo(4.796f, 21.911f, 4.575f, 21.691f, 4.409f, 21.426f)
                curveTo(4.101f, 20.933f, 3.961f, 20.246f, 3.879f, 19.437f)
                curveTo(3.743f, 18.081f, 3.747f, 16.001f, 3.752f, 12.977f)
                curveTo(3.753f, 12.337f, 3.754f, 11.655f, 3.754f, 10.929f)
                curveTo(3.754f, 9.047f, 3.821f, 7.529f, 4.034f, 6.319f)
                curveTo(4.247f, 5.107f, 4.62f, 4.13f, 5.288f, 3.397f)
                curveTo(5.965f, 2.656f, 6.866f, 2.252f, 7.959f, 2.028f)
                close()
                moveTo(5.511f, 6.579f)
                curveTo(5.322f, 7.653f, 5.254f, 9.062f, 5.254f, 10.929f)
                curveTo(5.254f, 11.673f, 5.253f, 12.366f, 5.252f, 13.011f)
                curveTo(5.247f, 16.037f, 5.244f, 18.014f, 5.372f, 19.287f)
                curveTo(5.45f, 20.071f, 5.57f, 20.454f, 5.681f, 20.631f)
                curveTo(5.725f, 20.701f, 5.754f, 20.717f, 5.765f, 20.722f)
                curveTo(5.781f, 20.73f, 5.828f, 20.75f, 5.943f, 20.75f)
                curveTo(6.092f, 20.75f, 6.387f, 20.646f, 6.873f, 20.309f)
                curveTo(7.304f, 20.009f, 7.771f, 19.611f, 8.283f, 19.173f)
                curveTo(8.316f, 19.145f, 8.349f, 19.116f, 8.383f, 19.088f)
                curveTo(8.917f, 18.632f, 9.499f, 18.14f, 10.071f, 17.765f)
                curveTo(10.625f, 17.401f, 11.285f, 17.066f, 11.97f, 17.066f)
                curveTo(12.656f, 17.066f, 13.316f, 17.401f, 13.87f, 17.765f)
                curveTo(14.442f, 18.14f, 15.024f, 18.632f, 15.558f, 19.088f)
                curveTo(15.591f, 19.116f, 15.625f, 19.145f, 15.658f, 19.173f)
                curveTo(16.17f, 19.611f, 16.636f, 20.009f, 17.068f, 20.309f)
                curveTo(17.554f, 20.646f, 17.849f, 20.75f, 17.997f, 20.75f)
                curveTo(18.112f, 20.75f, 18.16f, 20.73f, 18.176f, 20.722f)
                curveTo(18.187f, 20.717f, 18.216f, 20.701f, 18.26f, 20.631f)
                curveTo(18.371f, 20.454f, 18.49f, 20.071f, 18.569f, 19.287f)
                curveTo(18.697f, 18.014f, 18.694f, 16.037f, 18.689f, 13.011f)
                curveTo(18.688f, 12.366f, 18.686f, 11.673f, 18.686f, 10.929f)
                curveTo(18.686f, 9.062f, 18.618f, 7.653f, 18.429f, 6.579f)
                curveTo(18.241f, 5.508f, 17.944f, 4.846f, 17.545f, 4.409f)
                curveTo(17.153f, 3.98f, 16.587f, 3.683f, 15.681f, 3.498f)
                curveTo(14.762f, 3.309f, 13.563f, 3.25f, 11.97f, 3.25f)
                curveTo(10.378f, 3.25f, 9.179f, 3.309f, 8.26f, 3.498f)
                curveTo(7.354f, 3.683f, 6.788f, 3.98f, 6.396f, 4.409f)
                curveTo(5.997f, 4.846f, 5.7f, 5.508f, 5.511f, 6.579f)
                close()
            }
            path(
                fill = SolidColor(Color.White),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(7.792f, 9.218f)
                curveTo(7.792f, 8.803f, 8.128f, 8.468f, 8.542f, 8.468f)
                horizontalLineTo(15.398f)
                curveTo(15.812f, 8.468f, 16.147f, 8.803f, 16.147f, 9.218f)
                curveTo(16.147f, 9.632f, 15.812f, 9.968f, 15.398f, 9.968f)
                horizontalLineTo(8.542f)
                curveTo(8.128f, 9.968f, 7.792f, 9.632f, 7.792f, 9.218f)
                close()
            }
        }.build()

        return _Bookmark!!
    }

@Suppress("ObjectPropertyName")
private var _Bookmark: ImageVector? = null
