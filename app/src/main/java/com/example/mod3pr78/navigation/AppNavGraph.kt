package com.example.mod3pr78.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.mod3pr78.presentation.ui.screen.TodoDetailScreen
import com.example.mod3pr78.presentation.ui.screen.TodoListScreen
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel

@Composable
fun AppNavGraph(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(viewModel, navController)
        }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val todo = todos.find { it.id == id }
            if (todo != null) {
                TodoDetailScreen(todo, viewModel, navController)
            } else {
                Text("Задача не найдена")
            }
        }
    }
}
