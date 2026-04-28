package com.example.mod3pr78.domain.usecase

import com.example.mod3pr78.domain.repository.TodoRepository

class ImportInitialTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke() = repository.importInitialTodos()
}