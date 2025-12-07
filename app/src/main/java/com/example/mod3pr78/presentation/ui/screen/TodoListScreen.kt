package com.example.mod3pr78.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.presentation.viewmodel.ITodoViewModel
import androidx.compose.ui.platform.testTag

@Composable
fun TodoListScreen(viewModel: ITodoViewModel, navController: NavController) {
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(todos) { todo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate("detail/${todo.id}") },
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                        onCheckedChange = { viewModel.toggleTodo(todo.id) },
                        modifier = Modifier.testTag("todoCheckbox_${todo.id}")
                    )

                }
            }
        }
    }
}
