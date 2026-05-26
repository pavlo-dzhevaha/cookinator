package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Profile: ImageVector
    get() {
        if (_Profile != null) {
            return _Profile!!
        }
        _Profile = ImageVector.Builder(
            name = "Profile",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(14.212f, 7.762f)
                curveTo(14.212f, 10.406f, 12.049f, 12.525f, 9.349f, 12.525f)
                curveTo(6.651f, 12.525f, 4.486f, 10.406f, 4.486f, 7.762f)
                curveTo(4.486f, 5.119f, 6.651f, 3f, 9.349f, 3f)
                curveTo(12.049f, 3f, 14.212f, 5.119f, 14.212f, 7.762f)
                close()
                moveTo(2f, 17.917f)
                curveTo(2f, 15.47f, 5.386f, 14.858f, 9.349f, 14.858f)
                curveTo(13.335f, 14.858f, 16.699f, 15.491f, 16.699f, 17.94f)
                curveTo(16.699f, 20.388f, 13.313f, 21f, 9.349f, 21f)
                curveTo(5.364f, 21f, 2f, 20.367f, 2f, 17.917f)
                close()
                moveTo(16.173f, 7.849f)
                curveTo(16.173f, 9.195f, 15.76f, 10.451f, 15.036f, 11.495f)
                curveTo(14.961f, 11.602f, 15.028f, 11.747f, 15.159f, 11.77f)
                curveTo(15.341f, 11.8f, 15.528f, 11.818f, 15.718f, 11.822f)
                curveTo(17.617f, 11.87f, 19.32f, 10.674f, 19.791f, 8.871f)
                curveTo(20.489f, 6.197f, 18.441f, 3.795f, 15.834f, 3.795f)
                curveTo(15.551f, 3.795f, 15.28f, 3.824f, 15.016f, 3.877f)
                curveTo(14.98f, 3.885f, 14.941f, 3.902f, 14.921f, 3.932f)
                curveTo(14.896f, 3.972f, 14.914f, 4.023f, 14.94f, 4.056f)
                curveTo(15.723f, 5.132f, 16.173f, 6.442f, 16.173f, 7.849f)
                close()
                moveTo(19.317f, 13.702f)
                curveTo(20.593f, 13.947f, 21.432f, 14.444f, 21.779f, 15.169f)
                curveTo(22.074f, 15.764f, 22.074f, 16.453f, 21.779f, 17.048f)
                curveTo(21.248f, 18.17f, 19.534f, 18.532f, 18.867f, 18.625f)
                curveTo(18.729f, 18.644f, 18.619f, 18.529f, 18.633f, 18.393f)
                curveTo(18.974f, 15.281f, 16.266f, 13.805f, 15.566f, 13.466f)
                curveTo(15.536f, 13.449f, 15.53f, 13.426f, 15.533f, 13.411f)
                curveTo(15.535f, 13.401f, 15.547f, 13.386f, 15.57f, 13.383f)
                curveTo(17.085f, 13.354f, 18.715f, 13.559f, 19.317f, 13.702f)
                close()
            }
        }.build()

        return _Profile!!
    }

@Suppress("ObjectPropertyName")
private var _Profile: ImageVector? = null
