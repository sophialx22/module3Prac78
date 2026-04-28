package com.example.mod3pr78.domain.repository

import com.example.mod3pr78.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<TodoItem>>
    suspend fun addTodo(todo: TodoItem)
    suspend fun updateTodo(todo: TodoItem)
    suspend fun deleteTodo(todo: TodoItem)
    suspend fun toggleTodo(id: Int)
    suspend fun importInitialTodos()
}