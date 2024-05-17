package com.kate.pda_gtd.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Upsert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :id AND id != 0")
    suspend fun deleteTask(id: Long)

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): Category?
    @Query("DELETE FROM tasks WHERE category = :categoryName")
    suspend fun deleteAllTasksFromCategory(categoryName : String)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM tasks WHERE category = :category")
    fun getTasksByCategory(category: String): List<Task>


}