package com.example.doubletappexercise3.model

import java.io.Serializable

enum class PriorityHabit (val priority: String) {
    HIGH ("Высокий"),
    MEDIUM ("Средний"),
    LOW ("Низкий")
}

enum class TypeHabit (val type : String) {
    GOOD ("Хорошая"),
    BAD ("Плохая")
}

class Habit(
    val name: String,
    val description: String,
    val priority: PriorityHabit,
    val type: TypeHabit,
    val frequency: String,
    val periodicity: String
) : Serializable