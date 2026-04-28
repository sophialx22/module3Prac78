package com.example.mod3pr78.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoId: Int,
    viewModel: TodoViewModel,
    navController: NavController
) {
    val todo by viewModel.getTodoById(todoId).collectAsState(initial = null)

    if (todo == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Задача не найдена")
        }
        return
    }

    var isEditing by remember { mutableStateOf(false) }
    var editedTitle by remember { mutableStateOf(todo!!.title) }
    var editedDescription by remember { mutableStateOf(todo!!.description) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Редактирование" else "Детали задачи") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    if (!isEditing) {
                        IconButton(onClick = { isEditing = true }) {
                            Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                        }
                        IconButton(
                            onClick = {
                                viewModel.deleteTodo(todo!!)
                                navController.popBackStack()
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Удалить")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (isEditing) {
                OutlinedTextField(
                    value = editedTitle,
                    onValueChange = { editedTitle = it },
                    label = { Text("Название") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = editedDescription,
                    onValueChange = { editedDescription = it },
                    label = { Text("Описание") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val updatedTodo = todo!!.copy(
                            title = editedTitle,
                            description = editedDescription
                        )
                        viewModel.updateTodo(updatedTodo)
                        isEditing = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Сохранить")
                }
            } else {
                Text(
                    text = todo!!.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = todo!!.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (todo!!.isCompleted) "✓ Выполнено" else "○ Не выполнено",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}