package com.example.doubletappexercise3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import com.example.doubletappexercise3.R
import com.example.doubletappexercise3.model.Habit
import com.google.android.material.textfield.TextInputEditText

class EditHabitActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_habit)

        val etHabitName = findViewById<TextInputEditText>(R.id.etHabitName)
        val etHabitDescription = findViewById<TextInputEditText>(R.id.etHabitDescription)
        val spinnerPriority = findViewById<Spinner>(R.id.spinnerPriority)
        val radioGroupType = findViewById<RadioGroup>(R.id.radioGroupType)
        val etQuantity = findViewById<TextInputEditText>(R.id.etQuantity)
        val etPeriod = findViewById<TextInputEditText>(R.id.etPeriod)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val priorities = arrayOf("Низкий", "Средний", "Высокий")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        spinnerPriority.adapter = adapter

        val habit = intent.getSerializableExtra("habit") as? Habit
        val position = intent.getIntExtra("position", -1)

        if (habit != null) {
            etHabitName.setText(habit.name)
            etHabitDescription.setText(habit.description)
            spinnerPriority.setSelection(priorities.indexOf(habit.priority))
            if (habit.type == "Хорошая") {
                radioGroupType.check(R.id.radioGood)
            } else {
                radioGroupType.check(R.id.radioBad)
            }
            etQuantity.setText(habit.frequency)
            etPeriod.setText(habit.periodicity)
        }

        btnSave.setOnClickListener {
            val name = etHabitName.text.toString()
            val description = etHabitDescription.text.toString()
            val priority = spinnerPriority.selectedItem.toString()
            val type = if (radioGroupType.checkedRadioButtonId == R.id.radioGood) "Хорошая" else "Плохая"
            val frequency = etQuantity.text.toString()
            val periodicity = etPeriod.text.toString()

            val newHabit = Habit(
                name = name,
                description = description,
                priority = priority,
                type = type,
                frequency = frequency,
                periodicity = periodicity
            )

            val resultIntent = Intent().apply {
                putExtra("habit", newHabit)
                if (position != -1) {
                    putExtra("position", position)
                }
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}