package com.example.ticktap.ui.theme.deadlines

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ticktap.data.model.Deadline

class DeadlinesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val deadlines = listOf(
        Deadline(1, "Project X Presentation", "Present project findings to the class", System.currentTimeMillis() + 3600000, "This Week"),
        Deadline(2, "Marketing Plan Submission", "Submit draft marketing strategy", System.currentTimeMillis() + 8 * 3600000, "This Week"),
        Deadline(3, "UI Design Review", "Review wireframes and color scheme", System.currentTimeMillis() + 2 * 24 * 3600000, "This Week"),
        Deadline(4, "Team Progress Report", "Submit weekly progress report", System.currentTimeMillis() + 4 * 24 * 3600000, "This Week"),
        Deadline(5, "Budget Proposal Draft", "Draft initial budget for Q4", System.currentTimeMillis() + 12 * 24 * 3600000, "Next Month"),
        Deadline(6, "Year-End Strategy Meeting", "Discuss company goals and roadmap for next year", System.currentTimeMillis() + 45 * 24 * 3600000, "Later")
    )

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val category = when (position) {
            0 -> "This Week"
            1 -> "Next Month"
            else -> "Later"
        }
        val filteredDeadlines = deadlines.filter { it.category == category }
        return DeadlineListFragment.newInstance(filteredDeadlines)
    }
}