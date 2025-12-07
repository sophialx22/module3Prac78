package com.example.mod3pr78.domain.usecase
import com.example.mod3pr78.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) = repository.toggleTodo(id)
}
