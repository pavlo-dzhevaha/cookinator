package idp.cookinator.coreui.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Search: ImageVector
    get() {
        if (_Search != null) {
            return _Search!!
        }
        _Search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF303030)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(4.08f, 11.432f)
                curveTo(4.08f, 7.351f, 7.462f, 4.044f, 11.632f, 4.044f)
                curveTo(15.803f, 4.044f, 19.184f, 7.351f, 19.184f, 11.432f)
                curveTo(19.184f, 15.512f, 15.803f, 18.819f, 11.632f, 18.819f)
                curveTo(7.462f, 18.819f, 4.08f, 15.512f, 4.08f, 11.432f)
                close()
                moveTo(11.632f, 2.4f)
                curveTo(6.533f, 2.4f, 2.4f, 6.444f, 2.4f, 11.432f)
                curveTo(2.4f, 16.419f, 6.533f, 20.463f, 11.632f, 20.463f)
                curveTo(13.768f, 20.463f, 15.735f, 19.753f, 17.299f, 18.562f)
                lineTo(20.166f, 21.36f)
                curveTo(20.495f, 21.681f, 21.027f, 21.68f, 21.355f, 21.358f)
                curveTo(21.682f, 21.037f, 21.682f, 20.517f, 21.353f, 20.196f)
                lineTo(18.527f, 17.438f)
                curveTo(19.981f, 15.842f, 20.865f, 13.738f, 20.865f, 11.432f)
                curveTo(20.865f, 6.444f, 16.731f, 2.4f, 11.632f, 2.4f)
                close()
            }
        }.build()

        return _Search!!
    }

@Suppress("ObjectPropertyName")
private var _Search: ImageVector? = null
