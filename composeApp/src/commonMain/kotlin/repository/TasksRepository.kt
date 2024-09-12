package repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import logInfo
import model.Task

object TasksRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    fun findById(id: String): Task? {
        return _tasks.value.firstOrNull { it.id == id }
    }

    fun save(task: Task) {
        _tasks.update { tasks ->
            if (tasks.any { it.id == task.id }) {
                // Atualiza a tarefa existente e retorna a nova lista com a tarefa atualizada
                tasks.map { existingTask ->
                    if (existingTask.id == task.id) task else existingTask
                }
            } else {
                // Adiciona a nova tarefa à lista existente
                tasks + task
            }
        }
        logInfo("TasksRepository", "Saving task: $task")
        logInfo("TasksRepository", "Updated tasks size: ${_tasks.value.size}")
    }


    fun delete(id: String) {
        _tasks.update { tasks ->
            tasks.filterNot { it.id == id }
        }
    }
}

