package idp.cookinator.coreui.styling.attribute

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Theme color palette
 * [Figma](https://www.figma.com/design/yb5wLc6Bs3nHPmDEqreXDj/Food-Recipe-App--Community-?node-id=128-14&p=f&t=vpw1LavUxJedVavM-0)
 */
data class ThemeColor(
    val isLight: Boolean,
    val neutral: Neutral,
    val primary: Primary,
    val secondary: Secondary,
    val rating: Rating,
    val error: Error,
    val success: Success,
)

data class Neutral(
    val n100: Color,
    val n90: Color,
    val n80: Color,
    val n70: Color,
    val n60: Color,
    val n50: Color,
    val n40: Color,
    val n30: Color,
    val n20: Color,
    val n10: Color,
    val white: Color,
)

data class Primary(
    val p100: Color,
    val p90: Color,
    val p80: Color,
    val p70: Color,
    val p60: Color,
    val p50: Color,
    val p40: Color,
    val p30: Color,
    val p20: Color,
    val p10: Color,
    val p0: Color,
)

data class Secondary(
    val s100: Color,
    val s90: Color,
    val s80: Color,
    val s70: Color,
    val s60: Color,
    val s50: Color,
    val s40: Color,
    val s30: Color,
    val s20: Color,
    val s10: Color,
    val s0: Color,
)

data class Rating(
    val r100: Color,
)

data class Error(
    val e100: Color,
    val e10: Color,
)

data class Success(
    val s100: Color,
    val s10: Color,
)

val attributeLightThemeColor = ThemeColor(
    isLight = true,
    neutral = Neutral(
        n100 = Color(0xFF181818),
        n90 = Color(0xFF303030),
        n80 = Color(0xFF484848),
        n70 = Color(0xFF606060),
        n60 = Color(0xFF797979),
        n50 = Color(0xFF919191),
        n40 = Color(0xFFA9A9A9),
        n30 = Color(0xFFC1C1C1),
        n20 = Color(0xFFD9D9D9),
        n10 = Color(0xFFF1F1F1),
        white = Color(0xFFFFFFFF),
    ),
    primary = Primary(
        p100 = Color(0xFF711F1F),
        p90 = Color(0xFF882525),
        p80 = Color(0xFF9E2B2B),
        p70 = Color(0xFFB53232),
        p60 = Color(0xFFCB3838),
        p50 = Color(0xFFE23E3E),
        p40 = Color(0xFFE86565),
        p30 = Color(0xFFEE8B8B),
        p20 = Color(0xFFF3B2B2),
        p10 = Color(0xFFF9D8D8),
        p0 = Color(0xFFFCECEC),
    ),
    secondary = Secondary(
        s100 = Color(0xFF804E00),
        s90 = Color(0xFF995E00),
        s80 = Color(0xFFB36D00),
        s70 = Color(0xFFCC7D00),
        s60 = Color(0xFFE68C00),
        s50 = Color(0xFFFF9C00),
        s40 = Color(0xFFFFA61A),
        s30 = Color(0xFFFFBA4D),
        s20 = Color(0xFFFFCE80),
        s10 = Color(0xFFFFE1B3),
        s0 = Color(0xFFFFF5E6),
    ),
    rating = Rating(
        r100 = Color(0xFFFFB661),
    ),
    error = Error(
        e100 = Color(0xFFEE1133),
        e10 = Color(0xFFFDE7EB),
    ),
    success = Success(
        s100 = Color(0xFF31B057),
        s10 = Color(0xFFEAF7EE),
    ),
)

val attributeDarkThemeColor = attributeLightThemeColor.copy(
    isLight = false,
)

//region Material color scheme
fun ThemeColor.asMaterialColorScheme(): ColorScheme =
    when {
        isLight -> lightColorScheme(
            primary = primary.p100,
            primaryContainer = primary.p50,
            background = neutral.white,
            surface = neutral.white,
            surfaceContainerHigh = neutral.white,
            surfaceContainerLow = neutral.white,
            onSurface = neutral.n100,
            onSurfaceVariant = neutral.n90,
            error = error.e100,
        )

        else -> darkColorScheme(
            primary = primary.p100,
            primaryContainer = primary.p50,
            background = neutral.white,
            surface = neutral.white,
            surfaceContainerHigh = neutral.white,
            surfaceContainerLow = neutral.white,
            onSurface = neutral.n100,
            onSurfaceVariant = neutral.n90,
            error = error.e100,
        )
    }
//endregion Material color scheme
