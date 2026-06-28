package idp.cookinator.feature.recipe.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.recipe_detail_edit
import cookinator.localisation.generated.resources.recipe_detail_toggle_favorite
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.ArrowLeft
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.More
import org.jetbrains.compose.resources.stringResource

@Composable
private fun TopBarActionIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = icon,
        contentDescription = ContentDescription.ICON,
        tint = Theme.color.neutral.n90,
        modifier = modifier
            .size(Theme.size.s48)
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                role = Role.Button,
            )
            .padding(Theme.size.s12)
            .size(Theme.size.s24),
    )
}

@Composable
internal fun RecipeDetailTopBar(
    title: String,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    showFavorite: Boolean = true,
    showEdit: Boolean = false,
    onEdit: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var isOptionsMenuExpanded by remember { mutableStateOf(false) }
    val optionsMenuWidth = 240.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.color.neutral.n0),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            TopBarActionIcon(
                icon = Icons.ArrowLeft,
                onClick = onBack,
                modifier = Modifier.padding(bottom = Theme.size.s4),
            )
            Text(
                text = title,
                style = Theme.typography.bold.h4,
                color = Theme.color.neutral.n90,
                modifier = Modifier.padding(
                    start = Theme.size.s20,
                    bottom = Theme.size.s12,
                ),
            )
        }
        Box {
            TopBarActionIcon(
                icon = Icons.More,
                onClick = { isOptionsMenuExpanded = true },
            )
            DropdownMenu(
                expanded = isOptionsMenuExpanded,
                onDismissRequest = { isOptionsMenuExpanded = false },
                offset = DpOffset(
                    x = Theme.size.s48 - optionsMenuWidth,
                    y = Theme.size.s4,
                ),
                modifier = Modifier.width(optionsMenuWidth),
            ) {
                if (showEdit) {
                    DropdownMenuItem(
                        text = { Text(stringResource(Res.string.recipe_detail_edit)) },
                        onClick = {
                            isOptionsMenuExpanded = false
                            onEdit()
                        },
                    )
                }
                if (showFavorite) {
                    DropdownMenuItem(
                        text = { Text(stringResource(Res.string.recipe_detail_toggle_favorite)) },
                        onClick = {
                            isOptionsMenuExpanded = false
                            onToggleFavorite()
                        },
                    )
                }
            }
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    RecipeDetailTopBar(
        title = "How to make french toast",
        onBack = {},
        onToggleFavorite = {},
    )
}
