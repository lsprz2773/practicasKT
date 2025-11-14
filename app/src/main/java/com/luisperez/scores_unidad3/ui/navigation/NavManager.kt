package com.luisperez.scores_unidad3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisperez.scores_unidad3.domain.model.Destination
import com.luisperez.scores_unidad3.ui.views.AddStudent
import com.luisperez.scores_unidad3.ui.views.AverageView
import com.luisperez.scores_unidad3.ui.views.DashboardView
import com.luisperez.scores_unidad3.ui.views.EditStudentView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination ="Home"
    ) {
        composable("Home") { DashboardView() }
        composable("Edit-Student") { EditStudentView() }
        composable("Add-Student") { AddStudent() }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
){
    NavHost(navController, startDestination = startDestination.route) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when(destination){
                    Destination.STUDENT -> DashboardView()
                    Destination.SCORES -> AverageView()
                }
            }
        }
    }
}