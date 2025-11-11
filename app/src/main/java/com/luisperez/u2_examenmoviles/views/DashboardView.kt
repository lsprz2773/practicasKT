package com.luisperez.u2_examenmoviles.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Menu")
                }
            )
        }
    ) {
        DashboardViewContent(it, navController)
    }
}

@Composable
fun DashboardViewContent(paddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .width(300.dp),
            onClick = {
                navController.navigate("ThemeManager")
            }
        ) {
            Text("Ir a los ajustes del tema")
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Button(
            modifier = Modifier
                .width(300.dp),
            onClick = {
                navController.navigate("Form")
            }
        ) {
            Text("Ir al formulario")
        }
    }
}