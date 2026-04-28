package com.example.mod3pr78.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mod3pr78.presentation.ui.screen.AddTodoScreen
import com.example.mod3pr78.presentation.ui.screen.TodoDetailScreen
import com.example.mod3pr78.presentation.ui.screen.TodoListScreen
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel

@Composable
fun AppNavGraph(viewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(viewModel, navController)
        }

        composable("add") {
            AddTodoScreen(viewModel, navController)
        }

        composable(
            "detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
            TodoDetailScreen(todoId, viewModel, navController)
        }
    }
}