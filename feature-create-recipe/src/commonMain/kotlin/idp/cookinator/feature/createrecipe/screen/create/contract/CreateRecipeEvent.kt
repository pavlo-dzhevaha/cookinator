package idp.cookinator.feature.createrecipe.screen.create.contract

import idp.cookinator.coreui.viewmodel.base.BaseEvent
import idp.cookinator.feature.createrecipe.image.ImageSource

internal sealed interface CreateRecipeEvent : BaseEvent {
    data object NavigateBack : CreateRecipeEvent
    data class LaunchImagePicker(val source: ImageSource) : CreateRecipeEvent
}
