package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_image_camera
import cookinator.localisation.generated.resources.create_recipe_image_gallery
import cookinator.localisation.generated.resources.create_recipe_image_source_title
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.feature.createrecipe.image.ImageSource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRecipeImageSourceSheet(
    onDismiss: () -> Unit,
    onSourceSelected: (ImageSource) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(modifier = Modifier.padding(bottom = Theme.size.s24)) {
            Text(
                text = stringResource(Res.string.create_recipe_image_source_title),
                style = Theme.typography.bold.h5,
                color = Theme.color.neutral.n90,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Theme.size.s20, vertical = Theme.size.s8),
            )
            SheetOption(stringResource(Res.string.create_recipe_image_camera)) {
                onSourceSelected(ImageSource.Camera)
            }
            SheetOption(stringResource(Res.string.create_recipe_image_gallery)) {
                onSourceSelected(ImageSource.Gallery)
            }
        }
    }
}

@Composable
private fun SheetOption(
    label: String,
    onClick: () -> Unit,
) {
    Text(
        text = label,
        style = Theme.typography.regular.p,
        color = Theme.color.neutral.n90,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = Theme.size.s20, vertical = Theme.size.s16),
    )
}
