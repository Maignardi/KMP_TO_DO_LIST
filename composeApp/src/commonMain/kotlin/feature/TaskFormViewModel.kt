package feature

import androidx.lifecycle.ViewModel
import feature.tasksForm.TaskFormUiState
import generateUUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.Task
import repository.TasksRepository

class TaskFormViewModel(
    private val id: String? = null,
    private val repository: TasksRepository = TasksRepository
) : ViewModel() {
    // Declarar a variável task
    private var task: Task? = null

    private val _uiState = MutableStateFlow(TaskFormUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Se o id for nulo, não há tarefa; caso contrário, busca no repositório
        task = id?.let { repository.findById(it) }
        task?.let { t ->
            _uiState.update { currentState ->
                currentState.copy(
                    title = t.title,
                    description = t.description ?: "",
                    topAppBarTitle = "Editando tarefa",
                    isDeleteEnable = true
                )
            }
        }

        // Funções para mudança de título e descrição
        _uiState.update { currentState ->
            currentState.copy(
                onTitleChange = { newTitle ->
                    _uiState.update { it.copy(title = newTitle) }
                },
                onDescriptionChange = { newDescription ->
                    _uiState.update { it.copy(description = newDescription) }
                }
            )
        }
    }

    // Função para salvar tarefa
    fun save() {
        with(_uiState.value) {
            val newTask = Task(
                id = id ?: generateUUID(), // Gera um novo UUID se o id for nulo
                title = title,
                description = description,
                isDone = task?.isDone ?: false // Mantém o status de conclusão anterior
            )
            repository.save(newTask)
            _uiState.update {
                it.copy(title = "", description = "")
            }
        }
    }

    // Função para deletar tarefa
    fun delete() {
        id?.let {
            repository.delete(it)
        }
    }
}


