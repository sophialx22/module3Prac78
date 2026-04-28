package com.example.mod3pr78.data.repository

import com.example.mod3pr78.data.local.TodoDatabase
import com.example.mod3pr78.data.local.TodoJsonDataSource
import com.example.mod3pr78.data.model.TodoItemEntity
import com.example.mod3pr78.domain.model.TodoItem
import com.example.mod3pr78.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val context: android.content.Context,
    private val jsonDataSource: TodoJsonDataSource
) : TodoRepository {

    private val database = TodoDatabase.getDatabase(context)
    private val dao = database.todoDao()

    override fun getTodos(): Flow<List<TodoItem>> {
        return dao.getAllTodos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addTodo(todo: TodoItem) {
        dao.insertTodo(todo.toEntity())
    }

    override suspend fun updateTodo(todo: TodoItem) {
        dao.updateTodo(todo.toEntity())
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        dao.deleteTodo(todo.toEntity())
    }

    override suspend fun toggleTodo(id: Int) {
        val todo = dao.getTodoById(id)
        todo?.let {
            dao.updateTodo(it.copy(isCompleted = !it.isCompleted))
        }
    }

    override suspend fun importInitialTodos() {
        val existingTodos = dao.getAllTodos().firstOrNull()
        if (existingTodos.isNullOrEmpty()) {
            jsonDataSource.importTodosToDatabase()
        }
    }

    private fun TodoItemEntity.toDomain(): TodoItem {
        return TodoItem(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }

    private fun TodoItem.toEntity(): TodoItemEntity {
        return TodoItemEntity(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }
}