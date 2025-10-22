package com.example.ticktap.data

import com.example.ticktap.data.model.Deadline
import com.example.ticktap.data.model.Note
import com.example.ticktap.data.model.Task

class AppRepository(private val appDatabase: AppDatabase) {

    suspend fun insertTask(task: Task) {
        appDatabase.taskDao().insert(task)
    }

    suspend fun getAllTasks(): List<Task> {
        return appDatabase.taskDao().getAllTasks()
    }

    suspend fun insertNote(note: Note) {
        appDatabase.noteDao().insert(note)
    }

    suspend fun getAllNotes(): List<Note> {
        return appDatabase.noteDao().getAllNotes()
    }

    suspend fun insertDeadline(deadline: Deadline) {
        appDatabase.deadlineDao().insert(deadline)
    }

    suspend fun getAllDeadlines(): List<Deadline> {
        return appDatabase.deadlineDao().getAllDeadlines()
    }
}