package com.luisperez.scores_unidad3.domain.model

import android.graphics.drawable.Drawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    STUDENT("Student","Estudiante", Icons.Default.Person,"Student"),
    SCORES("Scores","Scores", Icons.Default.Leaderboard,"Scores")
}