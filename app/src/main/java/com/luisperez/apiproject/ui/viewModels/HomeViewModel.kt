package com.luisperez.apiproject.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luisperez.apiproject.data.model.PokemonResult
import com.luisperez.apiproject.data.remote.RetroFitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons(){
        viewModelScope.launch {
            try {
                val response = RetroFitClient.api.getPokemonList(limit = 20, offset = 0)
                _uiState.value = HomeUiState.Success(response.results)
            } catch (e: Exception){
                _uiState.value = HomeUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

sealed class HomeUiState {
    object Loading: HomeUiState()
    data class Success(val pokemons: List<PokemonResult>): HomeUiState()
    data class Error(val message: String): HomeUiState()
}