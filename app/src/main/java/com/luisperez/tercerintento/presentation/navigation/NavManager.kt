package com.luisperez.tercerintento.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luisperez.tercerintento.presentation.views.DetailsView
import com.luisperez.tercerintento.presentation.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)

        }
        composable("Details/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.LongType
            }
        )) {
            val id = it.arguments?.getLong("id")?:0L
            DetailsView(navController, id)
        }
    }
}