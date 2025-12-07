package com.example.mod3pr78

import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.repository.TodoRepository

class FakeRepository : TodoRepository {

    private val items = mutableListOf(
        TodoItem(1, "Task1", "Desc1", false),
        TodoItem(2, "Task2", "Desc2", false),
        TodoItem(3, "Task3", "Desc3", false)
    )

    override suspend fun getTodos(): List<TodoItem> = items

    override suspend fun toggleTodo(id: Int) {}
}
