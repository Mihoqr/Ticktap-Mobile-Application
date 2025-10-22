package com.example.ticktap.ui.theme.deadlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ticktap.R
import com.example.ticktap.data.model.Deadline
import com.example.ticktap.databinding.ItemDeadlineBinding
import java.text.SimpleDateFormat
import java.util.*

class DeadlineAdapter(private var deadlines: List<Deadline>) : RecyclerView.Adapter<DeadlineAdapter.DeadlineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeadlineViewHolder {
        val binding = ItemDeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeadlineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeadlineViewHolder, position: Int) {
        holder.bind(deadlines[position])
    }

    override fun getItemCount() = deadlines.size

    fun updateData(newDeadlines: List<Deadline>) {
        deadlines = newDeadlines
        notifyDataSetChanged()
    }

    inner class DeadlineViewHolder(private val binding: ItemDeadlineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(deadline: Deadline) {
            binding.tvDeadlineTitle.text = deadline.title
            binding.tvDeadlineDescription.text = deadline.description

            // Format date and time
            val sdf = SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault())
            binding.tvDeadlineDateTime.text = sdf.format(Date(deadline.dateTime))

            // Calculate and set due in text
            val diff = deadline.dateTime - System.currentTimeMillis()
            val days = (diff / (1000 * 60 * 60 * 24)).toInt()
            val hours = ((diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)).toInt()
            val minutes = ((diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60)).toInt()

            var dueInText = "Due in "
            if (days > 0) dueInText += "$days days "
            if (hours > 0) dueInText += "$hours hrs "
            if (minutes > 0) dueInText += "$minutes mins"

            binding.tvDeadlineDueIn.text = dueInText

            // Set category color
            val color = when (deadline.category) {
                "This Week" -> R.color.orange
                "Next Month" -> R.color.magenta
                else -> R.color.purple_primary
            }
            binding.vCategoryColor.setBackgroundColor(itemView.context.getColor(color))
        }
    }
}