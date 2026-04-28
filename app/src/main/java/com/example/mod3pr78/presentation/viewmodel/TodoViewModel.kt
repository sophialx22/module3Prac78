package com.example.mod3pr78.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mod3pr78.data.preferences.PreferencesRepository
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.repository.TodoRepository
import com.example.mod3pr78.domain.usecase.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val importInitialTodosUseCase: ImportInitialTodosUseCase,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val todos: StateFlow<List<TodoItem>> = getTodosUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val isCompletedColorEnabled: StateFlow<Boolean> = preferencesRepository.isCompletedColorEnabled
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init {
        viewModelScope.launch {
            importInitialTodosUseCase()
        }
    }

    fun addTodo(todo: TodoItem) {
        viewModelScope.launch {
            addTodoUseCase(todo)
        }
    }

    fun updateTodo(todo: TodoItem) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
        }
    }

    fun toggleCompletedColor() {
        viewModelScope.launch {
            preferencesRepository.setCompletedColorEnabled(!isCompletedColorEnabled.value)
        }
    }

    fun getTodoById(id: Int): Flow<TodoItem?> {
        return todos.map { list -> list.find { it.id == id } }
    }
}