package idp.cookinator.coreui.component.appbottombar.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import idp.cookinator.coreui.component.appbottombar.model.BottomBarElement
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.utils.defaultTween

@Composable
internal fun BottomBarItem(
    modifier: Modifier = Modifier,
    item: BottomBarElement,
    selected: Boolean,
    height: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(CircleShape)
            .clickable(
                enabled = !selected,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Crossfade(
            targetState = if (selected) item.iconActive else item.iconInactive,
            animationSpec = defaultTween(),
        ) { icon ->
            Image(
                imageVector = icon,
                contentDescription = ContentDescription.ICON,
                modifier = Modifier
                    .size(height)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    BottomBarItem(
        item = BottomBarElement.Home,
        selected = true,
        height = 48.dp,
        onClick = {}
    )
}
