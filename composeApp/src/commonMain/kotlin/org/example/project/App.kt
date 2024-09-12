package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import feature.TaskFormViewModel
import feature.tasksForm.TaskFormScreen
import feature.tasksList.TasksListScreen
import feature.tasksList.TasksListViewModel
import logInfo
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import repository.TasksRepository

@Composable
fun App() {
    MaterialTheme {
        PreComposeApp {
            // Inicializa o navigator e garante que ele seja lembrado corretamente
            val navigator = rememberNavigator()

            // Define o NavHost e as rotas de navegação
            NavHost(
                navigator = navigator,
                navTransition = NavTransition(),
                initialRoute = "tasksList"
            ) {
                scene("tasksList") {
                    // Inicializa o ViewModel apenas uma vez
                    val tasksListViewModel = remember { TasksListViewModel(TasksRepository) }

                    // Renderiza a tela da lista de tarefas
                    TasksListScreen(
                        tasksListViewModel = tasksListViewModel,
                        onNewTaskClick = {
                            // Verifica se o clique no botão de nova tarefa está chamando a navegação
                            logInfo("App", "Navigating to task form")
                            navigator.navigate("taskForm")
                        }
                    )
                }

                scene("taskForm/{id}?") { backStackEntry ->
                    val id: String? = backStackEntry.path<String>("id")

                    // Inicializa o ViewModel para o formulário de tarefas
                    val taskFormViewModel = remember { TaskFormViewModel(id = id, repository = TasksRepository) }
                    val uiState by taskFormViewModel.uiState.collectAsState()

                    // Renderiza a tela de formulário de tarefas
                    TaskFormScreen(
                        uiState = uiState,
                        onSaveClick = {
                            taskFormViewModel.save()
                            navigator.goBack()  // Navega de volta após salvar
                        },
                        onDeleteClick = {
                            taskFormViewModel.delete()
                            navigator.goBack()  // Navega de volta após deletar
                        }
                    )
                }
            }
        }
    }
}

