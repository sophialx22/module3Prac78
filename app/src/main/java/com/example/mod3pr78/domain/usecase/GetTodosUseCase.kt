package com.example.mod3pr78.domain.usecase
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.repository.TodoRepository

class GetTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<TodoItem> = repository.getTodos()
}
