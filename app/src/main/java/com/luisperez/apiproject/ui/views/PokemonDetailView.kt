package com.luisperez.apiproject.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.luisperez.apiproject.ui.viewModels.DetailUiState
import com.luisperez.apiproject.ui.viewModels.PokemonDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailView(pokemonId: String, onBack: () -> Unit, viewmodel: PokemonDetailViewModel = viewModel()) {
    LaunchedEffect(pokemonId) {
        viewmodel.fetchPokemonDetail(pokemonId)
    }

    val state by viewmodel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()){
            when(val currentState = state){
                is DetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is DetailUiState.Success -> {
                    val pokemon = currentState.pokemon

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding( top = innerPadding.calculateTopPadding())
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            AsyncImage(
                                model = pokemon.sprites.other?.officialArtwork?.frontHd ?: "",
                                contentDescription = pokemon.name,
                                modifier = Modifier.size(250.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(24.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = pokemon.name.replaceFirstChar{ it.uppercase() },
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "#${pokemon.id.toString().padStart(3, '0')}",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                pokemon.types.forEach{typeSlot ->
                                    SuggestionChip(
                                        onClick = {},
                                        label = {
                                            Text(typeSlot.type.name.uppercase())
                                        },
                                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){
                                PokemonAttributeItem(label = "Peso", value = "${pokemon.weight / 10.0} KG")
                                PokemonAttributeItem(label = "Altura", value = "${pokemon.height / 10.0} M")
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = "Estadisticas base",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            pokemon.stats.forEach{stat ->
                                StatBar(
                                    name = stat.stat.name,
                                    value = stat.value,
                                    max = 255
                                )
                            }

                        }
                    }
                }

                is DetailUiState.Error -> {
                    Text(
                        text = "Error: ${currentState.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
    }
}

@Composable
fun PokemonAttributeItem(label: String, value: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun StatBar(name: String, value: Int, max: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = when(name){
                "hp" -> "HP"
                "attack" -> "ATK"
                "defense" -> "DEF"
                "special-attack" -> "SATK"
                "special-defense" -> "SDEF"
                "speed" -> "SPD"
                else -> name.uppercase().take(3)
            },
            modifier = Modifier.width(50.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        LinearProgressIndicator(
            progress = { value / max.toFloat()},
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = if (value > 100) Color(0xFF4CAF50) else if (value < 50) Color(0xFFFFC107) else Color(0xFFF44336),
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}