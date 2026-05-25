package idp.cookinator.feature.splash.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import idp.cookinator.coreui.styling.attribute.ThemeColor

@Composable
internal fun RecipeCard(
    theme: ThemeColor,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                shadowElevation = 16.dp.toPx()
                shape = RoundedCornerShape(12.dp)
                clip = true
            }
            .background(color = theme.neutral.white)
            // Use responsive padding based on the container size
            .padding(12.dp)
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
                        color = theme.neutral.n20,
                        shape = RoundedCornerShape(6.dp)
                    )
            )

            Spacer(modifier = Modifier.weight(1f))

            // Recipe Title Line
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .weight(0.8f)
                    .background(
                        color = theme.neutral.n40,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.weight(0.6f))

            // Recipe Subtitle/Time-Line
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .weight(0.8f)
                    .background(
                        color = theme.neutral.n40,
                        shape = CircleShape
                    )
            )
        }
    }
}
