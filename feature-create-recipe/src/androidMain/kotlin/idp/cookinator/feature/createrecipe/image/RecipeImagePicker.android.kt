package idp.cookinator.feature.createrecipe.image

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
actual fun rememberRecipeImagePicker(
    onImagePicked: (String?) -> Unit,
): RecipeImagePickerLauncher {
    val context = LocalContext.current
    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        onImagePicked(uri?.let { copyToRecipeImagesDir(context, it) })
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { success ->
        val uri = cameraUri
        onImagePicked(if (success && uri != null) copyToRecipeImagesDir(context, uri) else null)
        cameraUri = null
    }

    return remember(context) {
        RecipeImagePickerLauncher { source ->
            when (source) {
                ImageSource.Gallery -> {
                    galleryLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
                    )
                }
                ImageSource.Camera -> {
                    val file = createImageFile(context)
                    val uri = androidx.core.content.FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        file,
                    )
                    cameraUri = uri
                    cameraLauncher.launch(uri)
                }
            }
        }
    }
}

private fun createImageFile(context: Context): File {
    val dir = File(context.filesDir, "recipe_images").apply { mkdirs() }
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    return File(dir, "recipe_$timestamp.jpg")
}

private fun copyToRecipeImagesDir(context: Context, uri: Uri): String? = runCatching {
    val dir = File(context.filesDir, "recipe_images").apply { mkdirs() }
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val target = File(dir, "recipe_$timestamp.jpg")
    context.contentResolver.openInputStream(uri)?.use { input ->
        target.outputStream().use { output -> input.copyTo(output) }
    }
    target.absolutePath
}.getOrNull()
