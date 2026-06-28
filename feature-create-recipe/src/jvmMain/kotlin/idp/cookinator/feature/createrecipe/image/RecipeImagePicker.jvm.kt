package idp.cookinator.feature.createrecipe.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.FileDialog
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.swing.SwingUtilities

@Composable
actual fun rememberRecipeImagePicker(
    onImagePicked: (String?) -> Unit,
): RecipeImagePickerLauncher {
    return remember {
        RecipeImagePickerLauncher { source ->
            when (source) {
                ImageSource.Camera -> onImagePicked(null)
                ImageSource.Gallery -> {
                    SwingUtilities.invokeLater {
                        val dialog = FileDialog(null as java.awt.Frame?, "Select image", FileDialog.LOAD)
                        dialog.file = "*.jpg;*.jpeg;*.png;*.webp"
                        dialog.isVisible = true
                        val selected = dialog.file
                        val directory = dialog.directory
                        if (selected != null && directory != null) {
                            val sourceFile = File(directory, selected)
                            onImagePicked(copyToRecipeImagesDir(sourceFile))
                        } else {
                            onImagePicked(null)
                        }
                    }
                }
            }
        }
    }
}

private fun copyToRecipeImagesDir(sourceFile: File): String? = runCatching {
    val dir = File(System.getProperty("user.home"), ".cookinator/recipe_images").apply { mkdirs() }
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val extension = sourceFile.extension.takeIf { it.isNotBlank() } ?: "jpg"
    val target = File(dir, "recipe_$timestamp.$extension")
    sourceFile.copyTo(target, overwrite = true)
    target.absolutePath
}.getOrNull()
