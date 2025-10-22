package com.example.ticktap.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ticktap.data.model.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>
}