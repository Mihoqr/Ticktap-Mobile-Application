package com.example.ticktap.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticktap.data.AppDatabase
import com.example.ticktap.data.AppRepository
import com.example.ticktap.data.model.Deadline
import com.example.ticktap.data.model.Note
import com.example.ticktap.data.model.Task
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        val appDatabase = AppDatabase.getDatabase(application)
        repository = AppRepository(appDatabase)
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun insertDeadline(deadline: Deadline) = viewModelScope.launch {
        repository.insertDeadline(deadline)
    }
}