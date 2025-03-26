package com.example.doubletappexercise3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doubletappexercise3.R
import com.example.doubletappexercise3.model.Habit

class HabitAdapter(
    var habits: MutableList<Habit>, var context: Context, private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<HabitAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.habit_name)
        val description: TextView = view.findViewById(R.id.habit_description)
        val priority: TextView = view.findViewById(R.id.habit_priority)
        val type: TextView = view.findViewById(R.id.habit_type)
        val periodicity: TextView = view.findViewById(R.id.habit_periodicity)
    }

    fun addHabit(habit: Habit) {
        habits.add(habit)
        notifyItemInserted(habits.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habits.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = habits[position].name
        holder.description.text = habits[position].description
        holder.priority.text = habits[position].priority.toString()
        holder.type.text = habits[position].type.toString()
        holder.periodicity.text = habits[position].periodicity

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }
}