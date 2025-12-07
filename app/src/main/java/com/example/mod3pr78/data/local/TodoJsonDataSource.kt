package com.example.mod3pr78.data.local
import android.content.Context
import com.example.mod3pr78.data.model.TodoItemDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoJsonDataSource(private val context: Context) {
    fun getTodos(): List<TodoItemDto> {
        return try {
            val json = context.assets.open("todos.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<TodoItemDto>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
