package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_exit_cancel
import cookinator.localisation.generated.resources.create_recipe_exit_discard
import cookinator.localisation.generated.resources.create_recipe_exit_message
import cookinator.localisation.generated.resources.create_recipe_exit_save_draft
import cookinator.localisation.generated.resources.create_recipe_exit_title
import idp.cookinator.coreui.styling.theme.Theme
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRecipeExitDialog(
    isEditMode: Boolean,
    onDismiss: () -> Unit,
    onDiscard: () -> Unit,
    onSaveDraft: () -> Unit,
) {
    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(Theme.size.s16)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Theme.size.s20),
            ) {
                Text(
                    text = stringResource(Res.string.create_recipe_exit_title),
                    style = Theme.typography.bold.h5,
                    color = Theme.color.neutral.n90,
                )
                Text(
                    text = stringResource(Res.string.create_recipe_exit_message),
                    style = Theme.typography.regular.label,
                    color = Theme.color.neutral.n70,
                    modifier = Modifier.padding(top = Theme.size.s8, bottom = Theme.size.s16),
                )
                TextButton(onClick = onDiscard, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(Res.string.create_recipe_exit_discard),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                if (!isEditMode) {
                    TextButton(onClick = onSaveDraft, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(Res.string.create_recipe_exit_save_draft),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
                TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(Res.string.create_recipe_exit_cancel),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}
