package com.example.mod3pr78.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItemEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)