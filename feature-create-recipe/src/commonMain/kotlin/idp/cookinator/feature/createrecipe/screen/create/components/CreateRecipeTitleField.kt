package idp.cookinator.feature.createrecipe.screen.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cookinator.localisation.generated.resources.Res
import cookinator.localisation.generated.resources.create_recipe_title_hint
import idp.cookinator.coreui.styling.theme.Theme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CreateRecipeTitleField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = Theme.typography.regular.label.copy(color = Theme.color.neutral.n90),
        placeholder = {
            Text(
                text = stringResource(Res.string.create_recipe_title_hint),
                style = Theme.typography.regular.label,
                color = Theme.color.neutral.n30,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Theme.color.neutral.n0,
            unfocusedContainerColor = Theme.color.neutral.n0,
            disabledContainerColor = Theme.color.neutral.n0,
            focusedIndicatorColor = Theme.color.primary.p50,
            unfocusedIndicatorColor = Theme.color.neutral.n20,
            disabledIndicatorColor = Theme.color.neutral.n20,
            cursorColor = Theme.color.neutral.n90,
        ),
        shape = RoundedCornerShape(Theme.size.s10),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.size.s20)
            .border(
                width = Theme.size.s1,
                color = Theme.color.neutral.n20,
                shape = RoundedCornerShape(Theme.size.s10),
            )
            .background(Theme.color.neutral.n0, RoundedCornerShape(Theme.size.s10)),
    )
}
