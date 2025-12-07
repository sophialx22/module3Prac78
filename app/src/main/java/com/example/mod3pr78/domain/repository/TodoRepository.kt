package com.example.mod3pr78.domain.repository

import com.example.mod3pr78.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}
