package idp.cookinator.feature.navigation.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope

@Composable
fun <T : NavKey> rememberSoloSceneStrategy(): SoloSceneStrategy<T> =
    remember { SoloSceneStrategy() }

/**
 * A [SceneStrategy] that displays only the last entry in the back stack. This is useful for
 * simple navigation flows where only one screen should be visible at a time.
 *
 * For navigation ignoring previous entries, consider that only last entry is last and need pop
 * all Scenes -> close app.
 *
 * @param T The type of the navigation keys used in the back stack.
 */
class SoloSceneStrategy<T : NavKey> : SceneStrategy<T> {
    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T> =
        SoloPaneScene(entries.last())
}

/**
 * A [Scene] that displays only a single [NavEntry]. It uses the content of the provided entry
 * and ignores any previous entries in the back stack.
 *
 * @param T The type of the navigation keys used in the back stack.
 * @property entry The [NavEntry] to be displayed in this scene.
 */
private data class SoloPaneScene<T : NavKey>(
    val entry: NavEntry<T>,
) : Scene<T> {
    override val key: Any = entry.contentKey

    override val previousEntries: List<NavEntry<T>> = emptyList()

    override val entries: List<NavEntry<T>> = listOf(entry)

    override val content: @Composable () -> Unit = { entry.Content() }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SoloPaneScene<*>

        return key == other.key &&
                entry == other.entry
    }

    override fun hashCode(): Int {
        return key.hashCode() * 31 +
                entry.hashCode() * 31
    }

    override fun toString(): String = "SoloPaneScene(key=$key, entry=$entry)"
}
