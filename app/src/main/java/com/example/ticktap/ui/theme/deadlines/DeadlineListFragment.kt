package com.example.ticktap.ui.theme.deadlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticktap.data.model.Deadline
import com.example.ticktap.databinding.FragmentDeadlineListBinding

class DeadlineListFragment : Fragment() {

    private var _binding: FragmentDeadlineListBinding? = null
    private val binding get() = _binding!!

    private var deadlines: List<Deadline> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeadlineListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDeadlines.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDeadlines.adapter = DeadlineAdapter(deadlines)
    }

    fun setDeadlines(deadlines: List<Deadline>) {
        this.deadlines = deadlines
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(deadlines: List<Deadline>): DeadlineListFragment {
            val fragment = DeadlineListFragment()
            fragment.setDeadlines(deadlines)
            return fragment
        }
    }
}