package com.example.ticktap.ui.theme.deadlines

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.example.ticktap.R
import com.example.ticktap.databinding.FragmentDeadlinesBinding

class DeadlinesFragment : Fragment() {

    private var _binding: FragmentDeadlinesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = DeadlinesPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "This Week"
                1 -> "Next Month"
                else -> "Later"
            }
        }.attach()

        binding.fabAddDeadline.setOnClickListener {
            startActivity(Intent(requireContext(), AddDeadlineActivity::class.java))
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