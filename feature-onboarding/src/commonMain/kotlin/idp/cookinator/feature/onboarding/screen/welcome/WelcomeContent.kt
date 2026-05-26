package idp.cookinator.feature.onboarding.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import cookinator.core_ui.generated.resources.img_welcome
import cookinator.localisation.generated.resources.onboarding_button
import cookinator.localisation.generated.resources.onboarding_description
import cookinator.localisation.generated.resources.onboarding_hint_count
import cookinator.localisation.generated.resources.onboarding_hint_title
import cookinator.localisation.generated.resources.onboarding_title
import idp.cookinator.coreui.component.button.primary.PrimaryButton
import idp.cookinator.coreui.component.spacer.SpacerHeight
import idp.cookinator.coreui.component.spacer.SpacerWeight
import idp.cookinator.coreui.component.spacer.SpacerWidth
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.utils.ContentDescription
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Star
import idp.cookinator.feature.onboarding.screen.welcome.contract.Intent
import idp.cookinator.feature.onboarding.screen.welcome.contract.State
import idp.cookinator.localisation.UiText.Companion.asUiText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import cookinator.core_ui.generated.resources.Res.drawable as DrawableRes
import cookinator.localisation.generated.resources.Res.string as StringRes

@Composable
internal fun WelcomeContent(
    modifier: Modifier = Modifier,
    state: State,
    onIntent: (Intent) -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(DrawableRes.img_welcome),
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
                    tint = Theme.color.neutral.n0,
                    modifier = Modifier
                        .size(Theme.size.s16),
                )
                SpacerWidth(Theme.size.s8)
                Text(
                    text = stringResource(StringRes.onboarding_hint_count),
                    style = Theme.typography.bold.p,
                    color = Theme.color.neutral.n0,
                )
                SpacerWidth(Theme.size.s4)
                Text(
                    text = stringResource(StringRes.onboarding_hint_title),
                    style = Theme.typography.regular.p,
                    color = Theme.color.neutral.n0,
                )
            }
            SpacerWeight()
            Text(
                text = stringResource(StringRes.onboarding_title),
                style = Theme.typography.bold.heading,
                color = Theme.color.neutral.n0,
                textAlign = TextAlign.Center,
            )
            SpacerHeight(Theme.size.s24)
            Text(
                text = stringResource(StringRes.onboarding_description),
                style = Theme.typography.regular.p,
                color = Theme.color.neutral.n0,
            )
            SpacerHeight(Theme.size.s40)
            PrimaryButton(
                text = StringRes.onboarding_button.asUiText,
                hasIcon = true,
                loading = state.isLoading,
            ) { onIntent(Intent.OnContinue) }
        }
    }
}

@Preview
@Composable
private fun Preview() = AppTheme {
    WelcomeContent(
        state = State.initialState,
        onIntent = {},
    )
}
