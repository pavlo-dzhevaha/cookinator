package idp.cookinator.coreui.styling.attribute

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cookinator.core_ui.generated.resources.Poppins_Regular
import cookinator.core_ui.generated.resources.Poppins_SemiBold
import cookinator.core_ui.generated.resources.Res
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.styling.theme.AppTheme
import org.jetbrains.compose.resources.Font

data class ThemeTypography(
    val bold: ThemeTextStyle,
    val regular: ThemeTextStyle,
)

data class ThemeTextStyle(
    val heading: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val p: TextStyle,
    val label: TextStyle,
    val small: TextStyle,
    val tiny: TextStyle,
) {
    fun with(weight: FontWeight) = copy(
        heading = heading.copy(fontWeight = weight),
        h1 = h1.copy(fontWeight = weight),
        h2 = h2.copy(fontWeight = weight),
        h3 = h3.copy(fontWeight = weight),
        h4 = h4.copy(fontWeight = weight),
        h5 = h5.copy(fontWeight = weight),
        p = p.copy(fontWeight = weight),
        label = label.copy(fontWeight = weight),
        small = small.copy(fontWeight = weight),
        tiny = tiny.copy(fontWeight = weight),
    )
}

@Composable
fun attributeTypography(): ThemeTypography {
    val appFontFamily = FontFamily(
        Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Poppins_Regular, FontWeight.Normal),
    )

    val themeTextStyle = remember {
        ThemeTextStyle(
            heading = TextStyle(
                fontSize = 56.sp,
                fontFamily = appFontFamily,
                lineHeight = 56.sp * 1.2,
            ),
            h1 = TextStyle(
                fontSize = 48.sp,
                fontFamily = appFontFamily,
                lineHeight = 48.sp * 1.2,
            ),
            h2 = TextStyle(
                fontSize = 40.sp,
                fontFamily = appFontFamily,
                lineHeight = 40.sp * 1.2,
            ),
            h3 = TextStyle(
                fontSize = 32.sp,
                fontFamily = appFontFamily,
                lineHeight = 32.sp * 1.2,
            ),
            h4 = TextStyle(
                fontSize = 24.sp,
                fontFamily = appFontFamily,
                lineHeight = 24.sp * 1.2,
            ),
            h5 = TextStyle(
                fontSize = 20.sp,
                fontFamily = appFontFamily,
                lineHeight = 20.sp * 1.4,
            ),
            p = TextStyle(
                fontSize = 16.sp,
                fontFamily = appFontFamily,
                lineHeight = 16.sp * 1.4,
            ),
            label = TextStyle(
                fontSize = 14.sp,
                fontFamily = appFontFamily,
                lineHeight = 14.sp * 1.4,
            ),
            small = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
            ),
            tiny = TextStyle(
                fontSize = 10.sp,
                fontFamily = appFontFamily,
            ),
        )
    }

    return remember {
        ThemeTypography(
            regular = themeTextStyle.with(FontWeight.Normal),
            bold = themeTextStyle.with(FontWeight.Bold),
        )
    }
}

@Preview(
    showBackground = true,
    heightDp = 1000,
)
@Composable
private fun Preview() = AppTheme {
    Column(
        verticalArrangement = Arrangement.spacedBy(Theme.size.s8),
        modifier = Modifier
            .padding(horizontal = Theme.size.s16),
    ) {
        Theme.typography.run {
            listOf(
                regular.heading to "Heading",
                regular.h1 to "H1",
                regular.h2 to "H2",
                regular.h3 to "H3",
                regular.h4 to "H4",
                regular.h5 to "H5",
                regular.p to "Paragraph",
                regular.label to "Label",
                regular.small to "Small",
                regular.tiny to "Tiny",
                bold.heading to "Heading (Bold)",
                bold.h1 to "H1 (Bold)",
                bold.h2 to "H2 (Bold)",
                bold.h3 to "H3 (Bold)",
                bold.h4 to "H4 (Bold)",
                bold.h5 to "H5 (Bold)",
                bold.p to "Paragraph (Bold)",
                bold.label to "Label (Bold)",
                bold.small to "Small (Bold)",
                bold.tiny to "Tiny (Bold)",
            ).forEach { (style, text) ->
                Text(
                    text = text,
                    style = style,
                )
            }
        }
    }
}

//region material3 typography
fun ThemeTypography.asMaterialTypography() = Typography(
    displayLarge = bold.heading,
    displayMedium = bold.h1,
    displaySmall = bold.h2,
    headlineLarge = bold.h3,
    headlineMedium = bold.h4,
    headlineSmall = bold.h5,
    bodyLarge = regular.p,
    bodyMedium = regular.label,
    bodySmall = regular.small,
    labelLarge = regular.label,
    labelMedium = regular.small,
    labelSmall = regular.tiny,
)
//endregion
