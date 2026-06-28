package idp.cookinator.feature.createrecipe.image

import androidx.compose.runtime.Composable

enum class ImageSource {
    Camera,
    Gallery,
}

fun interface RecipeImagePickerLauncher {
    fun launch(source: ImageSource)
}

@Composable
expect fun rememberRecipeImagePicker(
    onImagePicked: (String?) -> Unit,
): RecipeImagePickerLauncher
