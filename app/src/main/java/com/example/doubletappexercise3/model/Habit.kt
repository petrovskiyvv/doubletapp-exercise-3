package com.example.doubletappexercise3.model

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

enum class TypeHabit {
    GOOD,
    BAD
}

class Habit(
    val name: String,
    val description: String,
    val priority: Priority,
    val type: TypeHabit,
    val periodicity: String
) {
}