package com.luisperez.apiproject.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val title: String, val icon: ImageVector) {
    object Home: Routes("home", "Inicio", Icons.Rounded.Home)
    object Search: Routes("search", "Busqueda", Icons.Rounded.Search)
    object Favorites: Routes("favorites", "Favoritos", Icons.Rounded.Favorite)

    object PokemonDetail: Routes("detail/{pokemonId}", "Detalle", Icons.Rounded.Info){
        fun createRoute(pokemonId: String) = "detail/$pokemonId"
    }
}