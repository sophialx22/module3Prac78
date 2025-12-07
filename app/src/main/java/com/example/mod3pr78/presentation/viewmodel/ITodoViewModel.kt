package com.example.mod3pr78.presentation.viewmodel

import com.example.mod3pr78.domain.model.TodoItem
import kotlinx.coroutines.flow.StateFlow

interface ITodoViewModel {
    val todos: StateFlow<List<TodoItem>>
    fun toggleTodo(id: Int)
}
