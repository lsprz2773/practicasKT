package com.luisperez.coroutinesappa.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text( text = "Inicio")
                }
            )
        }
    ) {
        DasboardContent(it, navController)
    }
}

@Composable
fun DasboardContent(paddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate("ButtonsView")
            }
        ) {
            Text(text = "Redireccionar")
        }
    }
}