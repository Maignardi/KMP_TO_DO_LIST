package feature.tasksList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import logInfo
import model.Task

@Composable
fun TasksListScreen(
    tasksListViewModel: TasksListViewModel,
    modifier: Modifier = Modifier,
    onNewTaskClick: () -> Unit = {},  // Garantir que o callback seja chamado corretamente
    onTaskClick: (Task) -> Unit = {},
) {
    // Observando o fluxo de tarefas corretamente
    val tasks by tasksListViewModel.tasks.collectAsState()

    Box(modifier.fillMaxSize()) {
        ExtendedFloatingActionButton(
            onClick = {
                logInfo("TasksListScreen", "New Task button clicked")
                onNewTaskClick()
            },
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add new task icon")
                    Text(text = "New Task")
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .zIndex(1f) // Ajuste o zIndex para garantir que o botão fique acima de outros componentes
        )

        if (tasks.isEmpty()) {
            Text(
                text = "No tasks available",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(Modifier.fillMaxSize()) {
                items(tasks) { task ->
                    var showDescription by remember { mutableStateOf(false) }  // Controla o estado local para exibir a descrição

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDescription = !showDescription  // Alterna o estado de visibilidade da descrição
                                logInfo("TasksListScreen", "Task clicked: ${task.title}, showDescription: $showDescription")
                            }
                            .padding(8.dp)
                    ) {
                        // Exibição da tarefa com a possibilidade de marcar como concluída
                        Box(
                            Modifier
                                .size(30.dp)
                                .border(BorderStroke(2.dp, Color.Gray), RoundedCornerShape(8.dp))
                                .clickable {
                                    tasksListViewModel.toggleTaskCompletion(task.id)
                                }
                        ) {
                            if (task.isDone) {
                                Icon(Icons.Filled.Done, contentDescription = "Done", tint = Color.Green)
                            }
                        }
                        Column(Modifier.padding(8.dp)) {
                            Text(
                                text = task.title,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                            if (showDescription && task.description?.isNotBlank() == true) {
                                Text(
                                    text = task.description,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

