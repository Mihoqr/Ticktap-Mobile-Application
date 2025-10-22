package com.example.ticktap.ui.theme.settings

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.ticktap.R
import com.example.ticktap.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = requireActivity().getSharedPreferences("SettingsPrefs", Context.MODE_PRIVATE)

        // Theme switching
        val isDarkMode = sharedPrefs.getBoolean("isDarkMode", false)
        if (isDarkMode) {
            binding.rbDark.isChecked = true
        } else {
            binding.rbLight.isChecked = true
        }

        binding.rgTheme.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbLight -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharedPrefs.edit().putBoolean("isDarkMode", false).apply()
                }
                R.id.rbDark -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharedPrefs.edit().putBoolean("isDarkMode", true).apply()
                }
            }
        }

        // Daily summary time picker
        binding.btnDailySummaryTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    binding.btnDailySummaryTime.text = selectedTime
                    sharedPrefs.edit().putString("dailySummaryTime", selectedTime).apply()
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }

        // Snooze duration spinner
        val snoozeDurations = resources.getStringArray(R.array.snooze_durations)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, snoozeDurations)
        binding.spinnerSnoozeDuration.adapter = adapter

        val savedSnoozeDuration = sharedPrefs.getString("snoozeDuration", "15 minutes")
        val snoozeDurationPosition = snoozeDurations.indexOf(savedSnoozeDuration)
        if (snoozeDurationPosition != -1) {
            binding.spinnerSnoozeDuration.setSelection(snoozeDurationPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}