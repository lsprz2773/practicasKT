package com.luisperez.scores_unidad3.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.luisperez.scores_unidad3.domain.model.Student
import com.luisperez.scores_unidad3.ui.viewModels.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController, dashboardViewModel: DashboardViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estudiantes") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add Student")
            }
        }
    ) {
        DashboardViewContent(it, navController, dashboardViewModel, dashboardViewModel.students)
    }
}

@Composable
fun DashboardViewContent(paddingValues: PaddingValues, navController: NavController, dashboardViewModel: DashboardViewModel, students: List<Student>){
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
    ) {
        items(students){student ->
            Box(modifier = Modifier
                .clickable{
                    navController.navigate("")
                })
        }
    }
}