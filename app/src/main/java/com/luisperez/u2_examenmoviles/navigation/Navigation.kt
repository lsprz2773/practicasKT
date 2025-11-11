package com.luisperez.u2_examenmoviles.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisperez.u2_examenmoviles.dataStore.AppThemeStore
import com.luisperez.u2_examenmoviles.viewModels.FormViewModel
import com.luisperez.u2_examenmoviles.views.ChangeThemeView
import com.luisperez.u2_examenmoviles.views.DashboardView
import com.luisperez.u2_examenmoviles.views.FormView

@Composable
fun Navigation(viewModel: FormViewModel, appThemeStore: AppThemeStore, themeValue: Boolean){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            DashboardView(navController)
        }
        composable("ThemeManager") {
            ChangeThemeView(navController, appThemeStore, themeValue)
        }
        composable("Form") {
            FormView(navController, viewModel)
        }
    }
}