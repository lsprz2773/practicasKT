package com.luisperez.scores_unidad3.domain.model

data class Student(
    val id: Int,
    val name: String,
    val lastName: String,
    val grade: Int,
    val group: Char,
    val score: Double
)
