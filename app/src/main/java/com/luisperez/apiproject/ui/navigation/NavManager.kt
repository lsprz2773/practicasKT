package com.luisperez.apiproject.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luisperez.apiproject.ui.views.FavoritesView
import com.luisperez.apiproject.ui.views.HomeView
import com.luisperez.apiproject.ui.views.PokemonDetailView
import com.luisperez.apiproject.ui.views.SearchView

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavManager(){
    val navController = rememberNavController()

    val items = listOf(
        Routes.Home,
        Routes.Search,
        Routes.Favorites,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState =  true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Routes.Home.route) {
                HomeView(onPokemonClick = {pokemonName -> navController.navigate(Routes.PokemonDetail.createRoute(pokemonName))})
            }
            composable(Routes.Search.route) {
                SearchView(
                    onPokemonFound = { pokemonName ->
                        navController.navigate(Routes.PokemonDetail.createRoute(pokemonName))
                    }
                )
            }
            composable(Routes.Favorites.route) {
                FavoritesView(
                    onPokemonClick = { pokemonName ->
                        navController.navigate("detail/$pokemonName")
                    }
                )
            }
            composable(
                route = Routes.PokemonDetail.route, arguments =
                listOf(navArgument("pokemonId"){
                    type = NavType.StringType
                })
            ) {
                backStackEntry ->
                val pokemonId = backStackEntry.arguments?.getString("pokemonId")?: ""
                PokemonDetailView(pokemonId = pokemonId, onBack = { navController.popBackStack() })
            }
        }
    }
}