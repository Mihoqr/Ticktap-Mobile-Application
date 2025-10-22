package com.example.ticktap.ui.theme.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticktap.R
import com.example.ticktap.data.model.Deadline
import com.example.ticktap.data.model.Note
import com.example.ticktap.data.model.Task
import com.example.ticktap.databinding.FragmentHomeBinding
import com.example.ticktap.ui.theme.deadlines.DeadlineAdapter
import com.example.ticktap.ui.theme.notes.NoteAdapter
import com.example.ticktap.ui.theme.profile.ProfileFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        setupUpcomingDeadlines()
        setupTodaysTasks()
        setupRecentNotes()
    }

    private fun setupUpcomingDeadlines() {
        val deadlines = listOf(
            Deadline(1, "Project X Presentation", "Present project findings to the class", System.currentTimeMillis() + 3600000, "This Week"),
            Deadline(2, "Marketing Plan Submission", "Submit draft marketing strategy", System.currentTimeMillis() + 8 * 3600000, "This Week"),
            Deadline(3, "UI Design Review", "Review wireframes and color scheme", System.currentTimeMillis() + 2 * 24 * 3600000, "This Week")
        )
        binding.rvUpcomingDeadlines.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUpcomingDeadlines.adapter = DeadlineAdapter(deadlines)
    }

    private fun setupTodaysTasks() {
        val tasks = listOf(
            Task(1, "Morning Standup Meeting", isCompleted = true, timeString = "6:30 am"),
            Task(2, "Review Budget Report", isCompleted = true, timeString = "7:15 am"),
            Task(3, "Finalize Wireframe", isCompleted = true, timeString = "8:00 am"),
            Task(4, "Brainstorm Session", isCompleted = false, timeString = "10:10 am")
        )
        binding.rvTodaysTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodaysTasks.adapter = TaskAdapter(tasks)
    }

    private fun setupRecentNotes() {
        val notes = listOf(
            Note(1, "Team Meeting Agenda", "Topics: project updates, deadlines, and task assignments"),
            Note(2, "Ideas for App Launch", "Brainstorm features, color scheme options, and app name ideas.")
        )
        binding.rvRecentNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentNotes.adapter = NoteAdapter(notes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}