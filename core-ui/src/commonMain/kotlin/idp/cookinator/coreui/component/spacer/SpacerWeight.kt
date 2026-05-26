package idp.cookinator.coreui.component.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.SpacerWeight(
    weight: Float = 1f,
) {
    Spacer(
        modifier = Modifier.weight(weight),
    )
}

@Composable
fun RowScope.SpacerWeight(
    weight: Float = 1f,
) {
    Spacer(
        modifier = Modifier.weight(weight),
    )
}
