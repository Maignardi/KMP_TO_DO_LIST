package feature.tasksList

import model.Task

data class TasksListUiState(
    val tasks: List<Task> = emptyList(),
    val onTaskDoneChange: (Task) -> Unit = {},
)