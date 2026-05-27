package idp.cookinator.coreui.model

/**
 * Represents the state of a UI component, such as loading, success, or error.
 */
enum class UiState {
    LOADING,
    EMPTY,
    SUCCESS,
    ERROR;

    val isLoading: Boolean
        get() = this == LOADING

    val isEmpty: Boolean
        get() = this == EMPTY

    val isSuccess: Boolean
        get() = this == SUCCESS

    val isError: Boolean
        get() = this == ERROR
}
