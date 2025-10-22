package com.example.ticktap.ui.theme.notes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ticktap.data.model.Note
import com.example.ticktap.databinding.ActivityAddNoteBinding
import com.example.ticktap.viewmodel.AppViewModel
import com.example.ticktap.viewmodel.ViewModelFactory

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AppViewModel::class.java]

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnAddNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note(
            title = title,
            content = content
        )

        viewModel.insertNote(note)
        Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}