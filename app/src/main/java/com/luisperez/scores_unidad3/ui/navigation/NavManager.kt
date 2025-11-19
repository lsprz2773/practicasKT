package com.luisperez.scores_unidad3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luisperez.scores_unidad3.domain.model.Destination
import com.luisperez.scores_unidad3.ui.viewModels.DashboardViewModel
import com.luisperez.scores_unidad3.ui.views.AddStudent
import com.luisperez.scores_unidad3.ui.views.AverageView
import com.luisperez.scores_unidad3.ui.views.DashboardView
import com.luisperez.scores_unidad3.ui.views.EditStudentView

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel
){
    NavHost(navController, startDestination = startDestination.route) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when(destination){
                    Destination.STUDENT -> DashboardView(navController, dashboardViewModel)
                    Destination.SCORES -> AverageView()
                }
            }
        }
        composable("Edit-Student/{id}") { EditStudentView() }
        composable("Add-Student/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.LongType
            }
        )) { AddStudent() }
    }
}