package com.example.mod3pr78.data.local

import android.content.Context
import com.example.mod3pr78.data.model.TodoItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoJsonDataSource(private val context: Context) {

    fun getTodosFromJson(): List<TodoItemEntity> {
        return try {
            val json = context.assets.open("todos.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<TodoItemEntity>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun importTodosToDatabase() {
        val todos = getTodosFromJson()
        if (todos.isNotEmpty()) {
            val database = TodoDatabase.getDatabase(context)
            database.todoDao().insertAllTodos(todos)
        }
    }
}