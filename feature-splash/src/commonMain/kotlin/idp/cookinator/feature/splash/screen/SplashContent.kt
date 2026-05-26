package idp.cookinator.feature.splash.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import idp.cookinator.coreui.styling.theme.AppTheme
import idp.cookinator.coreui.styling.theme.Theme
import idp.cookinator.coreui.vector.Icons
import idp.cookinator.coreui.vector.Star
import idp.cookinator.feature.splash.screen.components.RecipeCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun SplashContent(
    modifier: Modifier = Modifier,
) {
    val colors = Theme.color

    // 1. Entrance Animation States
    val cardScale = remember { Animatable(0f) }
    val auraScale = remember { Animatable(0f) }
    val starScales = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) }
    )

    // 2. Continuous Animation States
    val infiniteTransition = rememberInfiniteTransition(label = "SplashInfinite")

    val auraRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "AuraRotation"
    )

    val cardHoverY by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "CardHover"
    )

    // 3. Orchestrate the Sequence
    LaunchedEffect(Unit) {
        launch {
            auraScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            )
        }
        launch {
            cardScale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
        starScales.forEachIndexed { index, animatable ->
            launch {
                delay(index * 150L)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
        }
    }

    // Use BoxWithConstraints to calculate responsive sizes based on screen bounds
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = colors.primary.p100),
    ) {
        val mainSize = min(maxWidth, maxHeight)
        // --- RESPONSIVE CALCULATIONS ---
        // The card takes up 35% of the screen size. Height is scaled to a standard 3:4 card ratio.
        val cardWidth = mainSize * 0.35f
        val cardHeight = cardWidth * 1.33f

        // Stars scale proportionally to the card
        val smallStarSize = cardWidth * 0.5f
        val largeStarSize = cardWidth * 0.65f

        // Aura size completely engulfs the card layout
        val auraSize = cardHeight * 1.6f

        // --- BACKGROUND AURA ---
        Canvas(
            modifier = Modifier
                .size(auraSize)
                .graphicsLayer {
                    scaleX = auraScale.value
                    scaleY = auraScale.value
                    rotationZ = auraRotation
                    alpha = 0.15f
                }
        ) {
            val strokeWidth = 4.dp.toPx()
            drawArc(
                color = colors.rating.r100,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                        floatArrayOf(30f, 30f)
                    )
                ),
                topLeft = Offset(strokeWidth, strokeWidth),
                size = Size(size.width - strokeWidth * 2, size.height - strokeWidth * 2)
            )
        }

        // --- CENTER BOX: Extracted Custom Recipe Card ---
        RecipeCard(
            theme = colors,
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight)
                .graphicsLayer {
                    scaleX = cardScale.value
                    scaleY = cardScale.value
                    translationY = cardHoverY * density
                }
        )

        // --- STARS ---
        // Left Star
        Icon(
            imageVector = Icons.Star,
            contentDescription = null,
            tint = colors.rating.r100,
            modifier = Modifier
                .size(smallStarSize)
                .offset(x = -(cardWidth * 0.7f), y = -(cardHeight * 0.5f))
                .graphicsLayer {
                    scaleX = starScales[0].value
                    scaleY = starScales[0].value
                    rotationZ = -20f
                    translationY = -cardHoverY * (density * 0.5f)
                }
        )

        // Center Star (Highest)
        Icon(
            imageVector = Icons.Star,
            contentDescription = null,
            tint = colors.rating.r100,
            modifier = Modifier
                .size(largeStarSize)
                .offset(x = 0.dp, y = -(cardHeight * 0.8f))
                .graphicsLayer {
                    scaleX = starScales[1].value
                    scaleY = starScales[1].value
                    translationY = -cardHoverY * (density * 0.8f)
                }
        )

        // Right Star
        Icon(
            imageVector = Icons.Star,
            contentDescription = null,
            tint = colors.rating.r100,
            modifier = Modifier
                .size(smallStarSize)
                .offset(x = (cardWidth * 0.7f), y = -(cardHeight * 0.5f))
                .graphicsLayer {
                    scaleX = starScales[2].value
                    scaleY = starScales[2].value
                    rotationZ = 20f
                    translationY = -cardHoverY * (density * 0.5f)
                }
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun Preview() = AppTheme {
    SplashContent()
}