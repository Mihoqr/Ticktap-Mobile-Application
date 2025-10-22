package com.example.ticktap.ui.theme.deadlines

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ticktap.data.model.Deadline
import com.example.ticktap.databinding.ActivityAddDeadlineBinding
import com.example.ticktap.viewmodel.AppViewModel
import com.example.ticktap.viewmodel.ViewModelFactory
import java.util.*

class AddDeadlineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDeadlineBinding
    private lateinit var viewModel: AppViewModel
    private var selectedDateTime: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AppViewModel::class.java]

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnPickDate.setOnClickListener {
            showDatePicker()
        }

        binding.btnPickTime.setOnClickListener {
            showTimePicker()
        }

        binding.btnAddDeadline.setOnClickListener {
            saveDeadline()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDateTime.set(Calendar.YEAR, year)
                selectedDateTime.set(Calendar.MONTH, month)
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateButton()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedDateTime.set(Calendar.MINUTE, minute)
                updateTimeButton()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        ).show()
    }

    private fun updateDateButton() {
        val dateFormat = java.text.SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        binding.btnPickDate.text = dateFormat.format(selectedDateTime.time)
    }

    private fun updateTimeButton() {
        val timeFormat = java.text.SimpleDateFormat("hh:mm a", Locale.getDefault())
        binding.btnPickTime.text = timeFormat.format(selectedDateTime.time)
    }

    private fun saveDeadline() {
        val title = binding.etTitle.text.toString()

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        val category = determineCategory(selectedDateTime.timeInMillis)

        val deadline = Deadline(
            title = title,
            description = "",
            dateTime = selectedDateTime.timeInMillis,
            category = category
        )

        viewModel.insertDeadline(deadline)
        Toast.makeText(this, "Deadline added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun determineCategory(timeInMillis: Long): String {
        val now = System.currentTimeMillis()
        val diff = timeInMillis - now
        val days = diff / (1000 * 60 * 60 * 24)

        return when {
            days <= 7 -> "This Week"
            days <= 30 -> "Next Month"
            else -> "Later"
        }
    }
}
