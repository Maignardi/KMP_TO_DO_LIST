package feature.tasksForm

data class  TaskFormUiState(
    val title: String = "",
    val description: String = "",
    val topAppBarTitle: String = "",
    val onTitleChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val isDeleteEnable: Boolean = false,
)