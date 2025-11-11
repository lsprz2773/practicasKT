package com.luisperez.u2_examenmoviles.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FloatButton(onClick:() -> Unit){
    FloatingActionButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Agregar"
        )
    }
}

@Composable
fun MainIconButton(icon: ImageVector, onClick: () -> Unit){
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon, contentDescription = null
        )
    }
}