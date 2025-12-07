package com.example.mod3pr78
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.repository.TodoRepository
import com.example.mod3pr78.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ToggleTodoTestRepository : TodoRepository {
    val items = mutableListOf(
        TodoItem(1, "A", "B", false)
    )

    override suspend fun getTodos(): List<TodoItem> = items

    override suspend fun toggleTodo(id: Int) {
        val i = items.indexOfFirst { it.id == id }
        items[i] = items[i].copy(isCompleted = !items[i].isCompleted)
    }
}

class ToggleTodoUseCaseTest {

    @Test
    fun `toggleTodo changes isCompleted`() = runBlocking {
        val repo = ToggleTodoTestRepository()
        val useCase = ToggleTodoUseCase(repo)

        useCase(1)

        assertEquals(true, repo.items[0].isCompleted)
    }
}
