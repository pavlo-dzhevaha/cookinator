package idp.cookinator.feature.createrecipe.screen.create

import androidx.compose.runtime.Composable
import idp.cookinator.coreui.viewmodel.components.MviStateProvider
import idp.cookinator.feature.createrecipe.image.rememberRecipeImagePicker
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeEvent
import idp.cookinator.feature.createrecipe.screen.create.contract.CreateRecipeIntent
import idp.cookinator.feature.navigation.extension.Navigator
import idp.cookinator.feature.navigation.extension.navigateUp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun CreateRecipeScreen(
    mode: CreateRecipeMode,
    navigator: Navigator,
    viewModel: CreateRecipeViewModel = koinViewModel { parametersOf(mode) },
) {
    val imagePicker = rememberRecipeImagePicker { path ->
        viewModel.onIntent(CreateRecipeIntent.OnImagePicked(path))
    }

    MviStateProvider(
        viewModel = viewModel,
        onEvent = { event ->
            when (event) {
                CreateRecipeEvent.NavigateBack -> navigator.navigateUp()
                is CreateRecipeEvent.LaunchImagePicker -> {
                    imagePicker.launch(event.source)
                }
            }
        },
    ) { state ->
        CreateRecipeContent(
            state = state,
            onIntent = viewModel::onIntent,
        )
    }
}
