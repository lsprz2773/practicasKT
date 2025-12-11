package com.luisperez.apiproject.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luisperez.apiproject.ui.components.PokemonCard
import com.luisperez.apiproject.ui.viewModels.FavoritesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesView(
    viewModel: FavoritesViewModel = viewModel(),
    onPokemonClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Favoritos") }
            )
        }
    ) { innerPadding ->
        FavoritesViewContent(innerPadding, viewModel, onPokemonClick)
    }
}
@Composable
fun FavoritesViewContent(
    paddingValues: PaddingValues,
    viewModel: FavoritesViewModel,
    onPokemonClick: (String) -> Unit
) {
    val favorites by viewModel.favoritesList.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (favorites.isEmpty()) {
            Text(
                text = "Aún no tienes favoritos",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Grid de favoritos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = 100.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(favorites) { pokemonEntity ->
                    PokemonCard(
                        pokemonName = pokemonEntity.name,
                        imageUrl = pokemonEntity.imageUrl,
                        pokemonId = pokemonEntity.id,
                        isFavorite = true,
                        onPokemonClick = { onPokemonClick(pokemonEntity.name) },
                        onFavoriteClick = {
                            viewModel.removeFavorite(pokemonEntity.id)
                        }
                    )
                }
            }
        }
    }
}