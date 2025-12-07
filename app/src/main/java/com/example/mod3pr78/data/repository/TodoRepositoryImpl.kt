package com.example.mod3pr78.data.repository
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.repository.TodoRepository
import com.example.mod3pr78.data.local.TodoJsonDataSource

class TodoRepositoryImpl(private val dataSource: TodoJsonDataSource) : TodoRepository {
    private var todosCache = mutableListOf<TodoItem>()
    override suspend fun getTodos(): List<TodoItem> {
        if (todosCache.isEmpty()) {
            todosCache = dataSource.getTodos().map { dto ->
                TodoItem(dto.id, dto.title, dto.description, dto.isCompleted)
            }.toMutableList()
        }
        return todosCache
    }
    override suspend fun toggleTodo(id: Int) {
        val index = todosCache.indexOfFirst { it.id == id }
        if (index != -1) {
            val old = todosCache[index]
            todosCache[index] = old.copy(isCompleted = !old.isCompleted)
        }
    }
}
