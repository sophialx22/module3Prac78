package com.example.mod3pr78

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mod3pr78.data.local.TodoJsonDataSource
import com.example.mod3pr78.data.preferences.PreferencesRepository
import com.example.mod3pr78.data.repository.TodoRepositoryImpl
import com.example.mod3pr78.domain.usecase.*
import com.example.mod3pr78.navigation.AppNavGraph
import com.example.mod3pr78.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonDataSource = TodoJsonDataSource(this)
        val repository = TodoRepositoryImpl(this, jsonDataSource)
        val preferencesRepository = PreferencesRepository(this)

        val getTodosUseCase = GetTodosUseCase(repository)
        val addTodoUseCase = AddTodoUseCase(repository)
        val updateTodoUseCase = UpdateTodoUseCase(repository)
        val deleteTodoUseCase = DeleteTodoUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)
        val importInitialTodosUseCase = ImportInitialTodosUseCase(repository)

        val viewModel = TodoViewModel(
            getTodosUseCase,
            addTodoUseCase,
            updateTodoUseCase,
            deleteTodoUseCase,
            toggleTodoUseCase,
            importInitialTodosUseCase,
            preferencesRepository
        )

        setContent {
            AppNavGraph(viewModel)
        }
    }
}