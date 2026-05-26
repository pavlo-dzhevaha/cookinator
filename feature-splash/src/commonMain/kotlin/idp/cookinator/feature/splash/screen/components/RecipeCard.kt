package idp.cookinator.feature.splash.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import idp.cookinator.coreui.component.spacer.SpacerWeight
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.LightDarkPreview
import idp.cookinator.coreui.styling.theme.Theme

@Composable
internal fun RecipeCard(
    modifier: Modifier = Modifier
) {
    val colors = Theme.color

    val elevation = Theme.size.s16
    val shadowShape = RoundedCornerShape(Theme.size.s12)
    Box(
        modifier = modifier
            .graphicsLayer {
                shadowElevation = elevation.toPx()
                shape = shadowShape
                clip = true
            }
            .background(color = Theme.color.neutral.n10)
            // Use responsive padding based on the container size
            .padding(Theme.size.s16)
    ) {
        // By using 'weight', the internal UI elements automatically resize
        // to fit whatever proportions the parent assigns to the RecipeCard.
        Column(modifier = Modifier.fillMaxSize()) {
            // Recipe Image Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
                    .background(
                        color = Theme.color.neutral.n20,
                        shape = RoundedCornerShape(Theme.size.s6)
                    )
            )

            SpacerWeight()

            // Recipe Title Line
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .weight(0.8f)
                    .background(
                        color = Theme.color.neutral.n40,
                        shape = CircleShape
                    )
            )

            SpacerWeight(0.6f)

            // Recipe Subtitle/Time-Line
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .weight(0.8f)
                    .background(
                        color = colors.neutral.n40,
                        shape = CircleShape
                    )
            )
        }
    }
}

@LightDarkPreview
@Composable
private fun Preview() = AppTheme {
    RecipeCard()
}
