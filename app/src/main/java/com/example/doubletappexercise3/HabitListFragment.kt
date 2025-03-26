package com.example.doubletappexercise3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doubletappexercise3.adapter.HabitAdapter
import com.example.doubletappexercise3.databinding.HabitListFragmentBinding
import com.example.doubletappexercise3.model.Habit
import com.example.doubletappexercise3.model.SharedViewModel

    class HabitListFragment : Fragment(R.layout.habit_list_fragment) {
    private var binding: HabitListFragmentBinding? = null
    private lateinit var habitAdapter: HabitAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HabitListFragmentBinding.bind(view)

        val recyclerView = binding?.habitsList
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        habitAdapter = HabitAdapter(mutableListOf(), requireContext()) { position ->
            val habit = sharedViewModel.habits.value?.get(position)
            val intent = Intent(requireContext(), EditHabitActivity::class.java).apply {
                putExtra("habit", habit)
                putExtra("position", position)
            }
            editHabitLauncher.launch(intent)
        }
        recyclerView?.adapter = habitAdapter

        sharedViewModel.habits.observe(viewLifecycleOwner) { habits ->
            Log.d("HabitListFragment", "Habits updated: ${habits.size}")
            habitAdapter.habits = habits.toMutableList()
            habitAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private val editHabitLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == 1) {
            val habit = result.data?.getSerializableExtra("habit") as Habit
            val position = result.data?.getIntExtra("position", -1) ?: -1

            if (position != -1) {
                sharedViewModel.updateHabit(position, habit)
            } else {
                sharedViewModel.addHabit(habit)
            }
        }
    }
}