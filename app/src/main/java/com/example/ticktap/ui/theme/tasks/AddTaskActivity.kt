package com.example.ticktap.ui.theme.tasks

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ticktap.R
import com.example.ticktap.data.model.Task
import com.example.ticktap.databinding.ActivityAddTaskBinding
import com.example.ticktap.viewmodel.AppViewModel
import com.example.ticktap.viewmodel.ViewModelFactory

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AppViewModel::class.java]

        // Setup spinner
        val priorities = resources.getStringArray(R.array.task_priorities)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        binding.spinnerPriority.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnAddTask.setOnClickListener {
            saveTask()
        }
    }

    private fun saveTask() {
        val title = binding.etTitle.text.toString()
        val details = binding.etDetails.text.toString()
        val priority = binding.spinnerPriority.selectedItem.toString()

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        val task = Task(
            title = title,
            details = details,
            priority = priority
        )

        viewModel.insertTask(task)
        Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}