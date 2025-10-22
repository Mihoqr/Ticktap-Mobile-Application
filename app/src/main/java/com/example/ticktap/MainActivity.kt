package com.example.ticktap

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ticktap.databinding.ActivityMainBinding
import com.example.ticktap.databinding.DialogAddOptionsBinding
import com.example.ticktap.ui.theme.auth.OnboardingActivity
import com.example.ticktap.ui.theme.deadlines.AddDeadlineActivity
import com.example.ticktap.ui.theme.deadlines.DeadlinesFragment
import com.example.ticktap.ui.theme.home.HomeFragment
import com.example.ticktap.ui.theme.notes.AddNoteActivity
import com.example.ticktap.ui.theme.notes.NotesFragment
import com.example.ticktap.ui.theme.settings.SettingsFragment
import com.example.ticktap.ui.theme.tasks.AddTaskActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = getSharedPreferences("TickTapPrefs", MODE_PRIVATE).getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_notes -> selectedFragment = NotesFragment()
                R.id.nav_deadlines -> selectedFragment = DeadlinesFragment()
                R.id.nav_settings -> selectedFragment = SettingsFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            }
            true
        }

        binding.fab.setOnClickListener {
            showAddOptionsDialog()
        }
    }

    private fun showAddOptionsDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = DialogAddOptionsBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.tvAddTask.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
            dialog.dismiss()
        }

        dialogBinding.tvAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
            dialog.dismiss()
        }

        dialogBinding.tvAddDeadline.setOnClickListener {
            startActivity(Intent(this, AddDeadlineActivity::class.java))
            dialog.dismiss()
        }

        dialog.show()
    }
}