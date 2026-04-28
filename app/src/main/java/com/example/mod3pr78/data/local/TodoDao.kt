package com.example.mod3pr78.data.local
import androidx.room.*
import com.example.mod3pr78.data.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items ORDER BY id ASC")
    fun getAllTodos(): Flow<List<TodoItemEntity>>
    @Query("SELECT * FROM todo_items WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoItemEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoItemEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTodos(todos: List<TodoItemEntity>)
    @Update
    suspend fun updateTodo(todo: TodoItemEntity)
    @Delete
    suspend fun deleteTodo(todo: TodoItemEntity)
    @Query("DELETE FROM todo_items")
    suspend fun deleteAllTodos()
}