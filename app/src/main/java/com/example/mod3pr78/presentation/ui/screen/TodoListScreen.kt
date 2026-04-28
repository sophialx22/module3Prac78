package com.example.mod3pr78.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    navController: NavController
) {
    val todos by viewModel.todos.collectAsState()
    val isCompletedColorEnabled by viewModel.isCompletedColorEnabled.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Добавить задачу")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Todo List") },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Цвет завершенных")
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = isCompletedColorEnabled,
                            onCheckedChange = { viewModel.toggleCompletedColor() }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(todos, key = { it.id }) { todo ->
                TodoItemCard(
                    todo = todo,
                    onClick = { navController.navigate("detail/${todo.id}") },
                    onToggle = { viewModel.toggleTodo(todo.id) },
                    isColorEnabled = isCompletedColorEnabled
                )
            }
        }
    }
}

@Composable
fun TodoItemCard(
    todo: TodoItem,
    onClick: () -> Unit,
    onToggle: () -> Unit,
    isColorEnabled: Boolean
) {
    val backgroundColor = if (isColorEnabled && todo.isCompleted) {
        Color(0xFFC8E6C9)
    } else {
        Color(0xFFE0E0E0)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = { onToggle() }
            )
        }
    }
}