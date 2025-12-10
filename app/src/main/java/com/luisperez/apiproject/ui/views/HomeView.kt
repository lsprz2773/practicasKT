package com.luisperez.apiproject.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import com.luisperez.apiproject.ui.viewModels.HomeUiState
import com.luisperez.apiproject.ui.viewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(onPokemonClick:(String)-> Unit, viewModel: HomeViewModel = viewModel()){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pokemones") }
            )
        }
    ) { innerPadding ->
        HomeViewContent(innerPadding, viewModel, onPokemonClick)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues, viewModel: HomeViewModel, onPokemonClick:(String)-> Unit){
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        when(val currentState = state){
            is HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is HomeUiState.Error -> {
                Text(
                    "Error: ${currentState.message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is HomeUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(top = paddingValues.calculateTopPadding() + 16.dp,
                        bottom = 100.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(currentState.pokemons){pokemon ->
                        val pokemonId = pokemon.url.split("/").dropLast(1).last().toIntOrNull() ?: 0
                        PokemonCard(
                            pokemonName = pokemon.name,
                            imageUrl = pokemon.getImageUrl(),
                            pokemonId = pokemonId,
                            isFavorite = false,
                            onPokemonClick = {
                                onPokemonClick(pokemon.name)
                            },
                            onFavoriteClick = {}
                        )
                    }
                }
            }
        }
    }

}