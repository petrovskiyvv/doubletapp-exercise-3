package com.example.doubletappexercise3.model

import java.io.Serializable

//enum class Priority {
//    HIGH,
//    MEDIUM,
//    LOW
//}
//
//enum class TypeHabit {
//    GOOD,
//    BAD
//}
// TODO ращобраться с работай енамов и изменить создание объектов

class Habit(
    val name: String,
    val description: String,
    val priority: String,
    val type: String,
    val frequency: String,
    val periodicity: String
) : Serializable