package com.example.mod3pr78.presentation.ui.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel

@Composable
fun TodoDetailScreen(
    todo: TodoItem,
    viewModel: TodoViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = todo.title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = todo.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (todo.isCompleted) "Выполнено" else "Не выполнено",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Назад")
        }
    }
}
