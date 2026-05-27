package idp.cookinator.coreui.component.appbottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import idp.cookinator.coreui.component.appbottombar.components.BottomBarCurveShape
import idp.cookinator.coreui.component.appbottombar.components.BottomBarItem
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement
import idp.cookinator.coreui.component.spacer.SpacerWeight
import idp.cookinator.coreui.utils.longestSide
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.utils.fauxCurveTopShadow
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Plus

private const val heightRatio = 86 / 812f
private const val itemSizeRatio = 24 / 86f
private const val topPaddingRatio = 14 / 86f
private const val addCircleSizeRatio = 48 / 86f
private const val addCircleIconSizeRatio = 24 / 86f
private const val curveRation = 40 / 86f

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    selected: BottomBarElement,
    onItemSelected: (BottomBarElement) -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    val density = LocalDensity.current

    val height = longestSide() * heightRatio
    val itemHeight = remember(height) { height * itemSizeRatio }
    val shadowHeight = remember(height) { height * topPaddingRatio }

    val curve = remember(height) {
        BottomBarCurveShape(
            ratio = with(density) { height.toPx() } * curveRation,
        )
    }

    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(height + bottomPadding)
                .padding(top = shadowHeight)
                .fauxCurveTopShadow(
                    shape = curve,
                    shadowColor = Theme.color.neutral.n100.copy(alpha = 0.05f),
                    blurRadius = shadowHeight / 2,
                )
                .background(
                    color = Theme.color.neutral.n0,
                    shape = curve,
                )
                .padding(bottom = bottomPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BottomBarElement.entries.forEach { item ->
                BottomBarItem(
                    item = item,
                    selected = selected == item,
                    height = itemHeight,
                    onClick = { onItemSelected(item) },
                    modifier = Modifier
                        .weight(1f) // Ensure they take equal space alongside Spacers
                )
                if (item == BottomBarElement.Saved) {
                    SpacerWeight()
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(height * addCircleSizeRatio)
                .background(
                    color = Theme.color.primary.p50,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .clickable(onClick = onAddClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Plus,
                contentDescription = ContentDescription.ICON,
                tint = Theme.color.neutral.n0,
                modifier = Modifier
                    .size(height * addCircleIconSizeRatio)
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    AppBottomBar(
        selected = BottomBarElement.Home,
    )
}
