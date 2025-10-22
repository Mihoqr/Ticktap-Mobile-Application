package com.example.ticktap.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ticktap.data.model.Deadline

@Dao
interface DeadlineDao {
    @Insert
    suspend fun insert(deadline: Deadline)

    @Query("SELECT * FROM deadlines")
    suspend fun getAllDeadlines(): List<Deadline>
}