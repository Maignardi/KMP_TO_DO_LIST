package feature.tasksList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import model.Task
import repository.TasksRepository

class TasksListViewModel(
    private val repository: TasksRepository
) : ViewModel() {

    // Expondo o fluxo de tarefas diretamente do repositório
    val tasks: StateFlow<List<Task>> = repository.tasks

    // Alternar a conclusão da tarefa
    fun toggleTaskCompletion(taskId: String) {
        val task = repository.findById(taskId)
        task?.let {
            val updatedTask = it.copy(isDone = !it.isDone)
            repository.save(updatedTask)
        }
    }

    // Excluir a tarefa
    fun deleteTask(taskId: String) {
        repository.delete(taskId)
    }
}
