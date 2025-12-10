package com.luisperez.apiproject.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luisperez.apiproject.ui.viewModels.SearchUiState
import com.luisperez.apiproject.ui.viewModels.SearchViewModel

@Composable
fun SearchView(
    onPokemonFound: (String) -> Unit,
    viewModel: SearchViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state) {
        if (state is SearchUiState.Success) {
            val pokemonName = (state as SearchUiState.Success).pokemonName
            onPokemonFound(pokemonName)
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text("Busca un Pokomon", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onQueryChange(it) },
            label = { Text("Nombre o ID") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    keyboardController?.hide()
                    viewModel.searchPokemon()
                }) {
                    Icon(Icons.Rounded.Search, contentDescription = "Buscar")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                viewModel.searchPokemon()
            })
        )

        Spacer(modifier = Modifier.height(32.dp))

        when (state) {
            is SearchUiState.Loading -> {
                CircularProgressIndicator()
            }
            is SearchUiState.Error -> {
                Text(
                    text = (state as SearchUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else -> {}
        }
    }
}