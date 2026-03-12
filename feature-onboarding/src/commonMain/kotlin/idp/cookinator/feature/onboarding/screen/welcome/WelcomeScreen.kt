package idp.cookinator.feature.onboarding.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import cookinator.core_ui.generated.resources.Res
import cookinator.core_ui.generated.resources.img_welcome
import idp.cookinator.coreui.component.button.primary.PrimaryButton
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.component.spacer.SpacerWeight
import idp.cookinator.coreui.component.spacer.SpacerWidth
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Star
import idp.cookinator.localisation.UiText.Companion.asUiText
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen(
    onStartTutorial: () -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(Res.drawable.img_welcome),
                contentDescription = ContentDescription.IMAGE,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding()
                    .padding(
                        top = Theme.size.s12,
                        bottom = Theme.size.s48,
                    ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Star,
                        contentDescription = ContentDescription.ICON,
                        tint = Theme.color.neutral.white,
                        modifier = Modifier
                            .size(Theme.size.s16),
                    )
                    SpacerWidth(Theme.size.s8)
                    Text(
                        text = "60k+",
                        style = Theme.typography.bold.p,
                        color = Theme.color.neutral.white,
                    )
                    SpacerWidth(Theme.size.s4)
                    Text(
                        text = "Premium recipes",
                        style = Theme.typography.regular.p,
                        color = Theme.color.neutral.white,
                    )
                }
                SpacerWeight()
                Text(
                    text = "Let’s\nCooking",
                    style = Theme.typography.bold.heading,
                    color = Theme.color.neutral.white,
                    textAlign = TextAlign.Center,
                )
                SpacerHeight(Theme.size.s24)
                Text(
                    text = "Find best recipes for cooking",
                    style = Theme.typography.regular.p,
                    color = Theme.color.neutral.white,
                )
                SpacerHeight(Theme.size.s40)
                PrimaryButton(
                    text = "Start cooking".asUiText,
                    hasIcon = true,
                    onClick = onStartTutorial,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    WelcomeScreen(
        onStartTutorial = {},
    )
}
