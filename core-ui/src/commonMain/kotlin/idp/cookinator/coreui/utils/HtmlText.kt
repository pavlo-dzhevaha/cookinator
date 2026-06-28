package idp.cookinator.coreui.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle

private val TAG_REGEX = Regex("<(/?)([a-zA-Z]+)[^>]*>", RegexOption.IGNORE_CASE)
private val ENTITY_REGEX = Regex("&([a-zA-Z]+|#[0-9]+|#x[0-9a-fA-F]+);")

private val HTML_ENTITIES = mapOf(
    "nbsp" to "\u00A0",
    "amp" to "&",
    "lt" to "<",
    "gt" to ">",
    "quot" to "\"",
    "apos" to "'",
)

fun parseSimpleHtml(html: String): AnnotatedString {
    val decoded = decodeHtmlEntities(html.trim())
    if (decoded.isEmpty()) return AnnotatedString("")

    return buildAnnotatedString {
        var boldDepth = 0
        var pendingParagraphBreak = false
        var cursor = 0

        TAG_REGEX.findAll(decoded).forEach { match ->
            appendTextSegment(
                text = decoded.substring(cursor, match.range.first),
                boldDepth = boldDepth,
                pendingParagraphBreak = pendingParagraphBreak,
            )
            pendingParagraphBreak = false
            cursor = match.range.last + 1

            val isClosing = match.groupValues[1].isNotEmpty()
            when (match.groupValues[2].lowercase()) {
                "b", "strong" -> if (isClosing) boldDepth = (boldDepth - 1).coerceAtLeast(0) else boldDepth++
                "p" -> if (isClosing) pendingParagraphBreak = true
                "br" -> append("\n")
            }
        }

        appendTextSegment(
            text = decoded.substring(cursor),
            boldDepth = boldDepth,
            pendingParagraphBreak = pendingParagraphBreak,
        )
    }
}

private fun AnnotatedString.Builder.appendTextSegment(
    text: String,
    boldDepth: Int,
    pendingParagraphBreak: Boolean,
) {
    if (pendingParagraphBreak && length > 0) append("\n\n")
    if (text.isEmpty()) return

    if (boldDepth > 0) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(text)
        }
    } else {
        append(text)
    }
}

private fun decodeHtmlEntities(text: String): String {
    if (!text.contains('&')) return text

    return ENTITY_REGEX.replace(text) { match ->
        val entity = match.groupValues[1]
        HTML_ENTITIES[entity.lowercase()]
            ?: when {
                entity.startsWith("#x", ignoreCase = true) ->
                    entity.substring(2).toIntOrNull(16)?.toChar()?.toString()
                entity.startsWith("#") ->
                    entity.substring(1).toIntOrNull()?.toChar()?.toString()
                else -> match.value
            }
            ?: match.value
    }
}

@Composable
fun HtmlText(
    html: String,
    style: TextStyle,
    color: Color,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val annotatedText = remember(html) { parseSimpleHtml(html) }
    if (annotatedText.text.isBlank()) return

    Text(
        text = annotatedText,
        style = style,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow,
        onTextLayout = onTextLayout,
    )
}
