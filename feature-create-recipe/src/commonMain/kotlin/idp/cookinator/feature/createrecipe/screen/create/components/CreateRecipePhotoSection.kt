package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_photo_hint
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Edit
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Plus
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CreateRecipePhotoSection(
    imagePath: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.size.s20)
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(Theme.size.s16))
            .background(Theme.color.neutral.n10)
            .clickable(onClick = onClick),
    ) {
        if (imagePath != null) {
            AsyncImage(
                model = imagePath,
                contentDescription = ContentDescription.IMAGE,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Theme.size.s12)
                    .size(Theme.size.s32)
                    .clip(CircleShape)
                    .background(Theme.color.neutral.n0)
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Edit,
                    contentDescription = ContentDescription.ICON,
                    tint = Theme.color.primary.p50,
                    modifier = Modifier.size(Theme.size.s16),
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
                modifier = Modifier.align(Alignment.Center),
            ) {
                Icon(
                    imageVector = Icons.Plus,
                    contentDescription = ContentDescription.ICON,
                    tint = Theme.color.neutral.n50,
                    modifier = Modifier.size(Theme.size.s24),
                )
                Text(
                    text = stringResource(Res.string.create_recipe_photo_hint),
                    style = Theme.typography.regular.label,
                    color = Theme.color.neutral.n50,
                )
            }
        }
    }
}
