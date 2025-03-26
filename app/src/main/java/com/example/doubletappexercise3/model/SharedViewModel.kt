package com.example.doubletappexercise3.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _habits = MutableLiveData<MutableList<Habit>>(mutableListOf())
    val habits: LiveData<MutableList<Habit>> get() = _habits

    fun addHabit(habit: Habit) {
        val currentHabits = _habits.value ?: mutableListOf()
        currentHabits.add(habit)
        _habits.value = currentHabits
    }

    fun updateHabit(position: Int, habit: Habit) {
        val currentHabits = _habits.value ?: mutableListOf()
        if (position in currentHabits.indices) {
            currentHabits[position] = habit
            _habits.value = currentHabits
        }
    }
}