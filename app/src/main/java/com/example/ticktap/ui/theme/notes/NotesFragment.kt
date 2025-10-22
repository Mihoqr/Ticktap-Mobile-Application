package com.example.ticktap.ui.theme.notes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticktap.R
import com.example.ticktap.data.model.Note
import com.example.ticktap.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notes = listOf(
            Note(1, "Team Meeting Agenda", "Topics: project updates, deadlines, and task assignments"),
            Note(2, "Ideas for App Launch", "Brainstorm features, color scheme options, and app name ideas."),
            Note(3, "Client Feedback", "Client suggested simpler UI and clearer deadline tracking."),
            Note(4, "Weekly Goals", "Topics: project updates, deadlines, and task assignments")
        )

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = NoteAdapter(notes)

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), AddNoteActivity::class.java))
        }

        binding.toolbar.findViewById<View>(R.id.btnSearch).setOnClickListener {
            Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
        }

        binding.toolbar.findViewById<View>(R.id.btnFilter).setOnClickListener {
            Toast.makeText(requireContext(), "Filter clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}