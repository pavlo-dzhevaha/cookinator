package idp.cookinator.feature.home.screen.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import idp.cookinator.coreui.component.spacer.SpacerWidth
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.utils.fauxCurveTopShadow
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Plus
import idp.cookinator.feature.home.screen.components.bottombar.components.BottomBarCurveShape
import idp.cookinator.feature.home.screen.components.bottombar.components.BottomBarItem
import idp.cookinator.feature.home.screen.components.bottombar.model.BottomBarElement

private const val horizontalPaddingRatio = 32 / 375f
private const val heightRatio = 86 / 812f
private const val itemSizeRatio = 40 / 86f
private const val topPaddingRatio = 14 / 86f
private const val itemSpacingRatio = 24 / 375f
private const val addCircleSizeRatio = 48 / 86f
private const val addCircleIconSizeRatio = 24 / 86f
private const val curveRation = 40 / 86f

@Composable
internal fun BottomBar(
    modifier: Modifier = Modifier,
    selected: BottomBarElement,
    onItemSelected: (BottomBarElement) -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    val (screenWidth, screenHeight) = LocalWindowInfo.current.containerDpSize
    val density = LocalDensity.current
    val mainSize = if (screenWidth < screenHeight) screenWidth else screenHeight
    val itemSpacing = mainSize * itemSpacingRatio

    val icons = BottomBarElement.entries

    val height = screenHeight * heightRatio
    val itemHeight = height * itemSizeRatio
    val shadowHeight = height * topPaddingRatio

    val curve = BottomBarCurveShape(
        modifier = with(density) { height.toPx() } * curveRation,
    )

    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .height(height)
                .padding(top = shadowHeight)
                .fauxCurveTopShadow(
                    shape = curve,
                    shadowColor = Theme.color.system.black.copy(alpha = 0.05f),
                    blurRadius = shadowHeight / 2,
                )
                .background(color = Theme.color.system.white, shape = curve)
                .padding(horizontal = screenWidth * horizontalPaddingRatio),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icons.forEach { item ->
                BottomBarItem(
                    item = item,
                    selected = selected == item,
                    height = itemHeight,
                    onClick = { onItemSelected(item) },
                    modifier = Modifier
                        .weight(1f) // Ensure they take equal space alongside Spacers
                )

                // Add spacer between items and at the edges
                when (item) {
                    BottomBarElement.Home, BottomBarElement.Notifications -> SpacerWidth(itemSpacing)
                    BottomBarElement.Saved -> Spacer(modifier = Modifier.weight(1f))
                    BottomBarElement.Profile -> {
                        /* No-op */
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(height * addCircleSizeRatio)
                .background(Theme.color.primary.p50, shape = CircleShape)
                .clickable(onClick = onAddClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Plus,
                contentDescription = ContentDescription.ICON,
                tint = Theme.color.system.white,
                modifier = Modifier
                    .size(height * addCircleIconSizeRatio)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun Preview() = AppTheme {
    BottomBar(
        selected = BottomBarElement.Home,
    )
}