package com.example.mod3pr78.presentation.ui.screen

import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.presentation.viewmodel.ITodoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeTodoViewModel(
    initialTodos: List<TodoItem>
) : ITodoViewModel {
    private val _todos = MutableStateFlow(initialTodos)
    override val todos: StateFlow<List<TodoItem>> = _todos
    override fun toggleTodo(id: Int) {
        val updatedList = _todos.value.map { todo ->
            if (todo.id == id) todo.copy(isCompleted = !todo.isCompleted) else todo
        }
        _todos.value = updatedList
    }
}
