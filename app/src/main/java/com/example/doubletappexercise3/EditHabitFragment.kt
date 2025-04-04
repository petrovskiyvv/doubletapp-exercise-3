package com.example.doubletappexercise3

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.doubletappexercise3.databinding.EditHabitFragmentBinding
import com.example.doubletappexercise3.model.Habit
import com.example.doubletappexercise3.model.PriorityHabit
import com.example.doubletappexercise3.model.SharedViewModel
import com.example.doubletappexercise3.model.TypeHabit

class EditHabitFragment : Fragment(R.layout.edit_habit_fragment) {
    private var binding: EditHabitFragmentBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditHabitFragmentBinding.bind(view)

        val habit = arguments?.getSerializable("habit") as? Habit
        val currentPosition = arguments?.getInt("position", -1) ?: -1
        habit?.let { populateFields(it) }


        val btnSave = binding?.btnSave
        btnSave?.setOnClickListener {
            val habit = createHabitFromInput()
            Log.d("EditHabitFragment", "Saving habit: $habit")
            if (currentPosition != -1) {
                sharedViewModel.updateHabit(currentPosition, habit)
            } else {
                sharedViewModel.addHabit(habit)
            }
            parentFragmentManager.popBackStack()
        }
    }

    private fun createHabitFromInput(): Habit {
        val habitName = binding?.habitName?.text.toString()
        val habitDescription = binding?.habitDescription?.text.toString()
        val selectedPriority = binding?.spinnerPriority?.selectedItem?.toString()
        val priorityHabit = when (selectedPriority) {
            "Высокий" -> PriorityHabit.HIGH
            "Средний" -> PriorityHabit.MEDIUM
            "Низкий" -> PriorityHabit.LOW
            else -> throw IllegalArgumentException("Unknown priority")
        }
        val radioGroupType = binding?.radioGroupType
        val selectedRadioButtonId = radioGroupType?.checkedRadioButtonId ?: -1
        val typeHabit = if (selectedRadioButtonId != -1) {
            val selectedRadioButton = view?.findViewById<RadioButton>(selectedRadioButtonId)
            when (selectedRadioButton?.text.toString()) {
                "Хорошая" -> TypeHabit.GOOD
                "Плохая" -> TypeHabit.BAD
                else -> throw IllegalArgumentException("Unknown habit type")
            }
        } else {
            throw IllegalStateException("No habit type selected")
        }
        val quantity = binding?.quantity?.text.toString()
        val period = binding?.period?.text.toString()
        return Habit(habitName, habitDescription, priorityHabit, typeHabit, quantity, period)
    }

    private fun populateFields(habit: Habit) {
        binding?.habitName?.setText(habit.name)
        binding?.habitDescription?.setText(habit.description)

        // Устанавливаем приоритет
        val priorityPosition = when (habit.priority) {
            PriorityHabit.HIGH -> 0
            PriorityHabit.MEDIUM -> 1
            PriorityHabit.LOW -> 2
        }
        binding?.spinnerPriority?.setSelection(priorityPosition)

        // Устанавливаем тип привычки
        val typeRadioButtonId = when (habit.type) {
            TypeHabit.GOOD -> R.id.radioGood
            TypeHabit.BAD -> R.id.radioBad
        }
        binding?.radioGroupType?.check(typeRadioButtonId)
        binding?.quantity?.setText(habit.frequency)
        binding?.period?.setText(habit.periodicity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}