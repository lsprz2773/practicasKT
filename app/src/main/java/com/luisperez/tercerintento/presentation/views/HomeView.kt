package com.luisperez.tercerintento.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisperez.tercerintento.data.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController){
    var students = listOf<Student>(
        Student(1L, "Emilia", "Jefa de grupo"),
        Student(2L, "Nadia", "Amiga de Emilia"),
        Student(3L, "Luis", "Amigo de la amiga de Emilia"),
        Student(4L, "Antonio", "Si"),
        Student(5L, "Angel", "No"),
        Student(6L, "Emma", "Descripcion")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DashBoard") }
            )
        }
    ) {
        Content(it, students, navController)
    }
}

@Composable
fun Content(paddingValues: PaddingValues, students: List<Student>, navController: NavController){
    LazyColumn (
        modifier = Modifier
            .padding(paddingValues).padding(horizontal = 10.dp)
    ) {
        items(students){student ->
            Box(
                modifier = Modifier
                    .clickable{
                        navController.navigate("Details/${student.id}")
                    }
                    .size(90.dp)
                    .background(color = Color.Yellow, CircleShape)
                    .wrapContentSize(Alignment.Center)
                    .padding(10.dp)
            ){
                Column {
                    Text(student.name, color = Color.Black)
                }
            }
                Spacer(modifier = Modifier.height(10.dp))
        }
    }
}