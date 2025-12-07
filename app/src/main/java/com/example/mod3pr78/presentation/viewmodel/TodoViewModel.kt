package com.example.mod3pr78.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.usecase.GetTodosUseCase
import com.example.mod3pr78.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel(), ITodoViewModel {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    override val todos: StateFlow<List<TodoItem>> get() = _todos

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            _todos.value = getTodosUseCase()
        }
    }

    override fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            _todos.value = getTodosUseCase()
        }
    }
}
