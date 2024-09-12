package feature.tasksForm

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskFormScreen(
    uiState: TaskFormUiState,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Column(modifier) {
        // Top bar with title and action icons
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xff68ddff)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiState.topAppBarTitle,
                    Modifier
                        .weight(1f)
                        .padding(8.dp),
                    fontSize = 20.sp
                )
                Row(
                    Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (uiState.isDeleteEnable) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete task icon",
                            Modifier
                                .clip(CircleShape)
                                .clickable {
                                    onDeleteClick()
                                }
                                .padding(4.dp)
                        )
                    }
                    Icon(
                        Icons.Filled.Done,
                        contentDescription = "Save task icon",
                        Modifier
                            .clip(CircleShape)
                            .clickable {
                                onSaveClick()
                            }
                            .padding(4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        // Title field
        BasicTextField(
            value = uiState.title,
            onValueChange = uiState.onTitleChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                if (uiState.title.isEmpty()) {
                    Text(
                        text = "Title",
                        style = TextStyle(fontSize = 24.sp, color = Color.Gray.copy(alpha = 0.5f))
                    )
                }
                innerTextField()
            },
            textStyle = TextStyle(fontSize = 24.sp)
        )

        Spacer(modifier = Modifier.size(16.dp))

        // Description field
        BasicTextField(
            value = uiState.description,
            onValueChange = uiState.onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                if (uiState.description.isEmpty()) {
                    Text(
                        text = "Description",
                        style = TextStyle(fontSize = 18.sp, color = Color.Gray.copy(alpha = 0.5f))
                    )
                }
                innerTextField()
            },
            textStyle = TextStyle(fontSize = 18.sp)
        )
    }
}
