package com.example.mod3pr78
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mod3pr78.data.local.TodoJsonDataSource
import com.example.mod3pr78.data.repository.TodoRepositoryImpl
import com.example.mod3pr78.domain.usecase.GetTodosUseCase
import com.example.mod3pr78.domain.usecase.ToggleTodoUseCase
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel
import com.example.mod3pr78.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataSource = TodoJsonDataSource(this)
        val repository = TodoRepositoryImpl(dataSource)
        val getTodos = GetTodosUseCase(repository)
        val toggleTodo = ToggleTodoUseCase(repository)
        val viewModel = TodoViewModel(getTodos, toggleTodo)
        setContent {
            AppNavGraph(viewModel)
        }
    }
}
