package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_save
import idp.cookinator.coreui.component.button.primary.PrimaryButton
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.localisation.UiText.Companion.asUiText
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CreateRecipeBottomBar(
    enabled: Boolean,
    loading: Boolean,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.color.neutral.n0)
            .padding(
                horizontal = Theme.size.s20,
                vertical = Theme.size.s16,
            ),
    ) {
        PrimaryButton(
            text = stringResource(Res.string.create_recipe_save).asUiText,
            enabled = enabled,
            loading = loading,
            onClick = onSave,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
