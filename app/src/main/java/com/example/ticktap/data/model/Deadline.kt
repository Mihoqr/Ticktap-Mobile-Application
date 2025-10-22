package com.example.ticktap.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deadlines")
data class Deadline(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val dateTime: Long,
    val category: String = "This Week" // This Week, Next Month, Later
)