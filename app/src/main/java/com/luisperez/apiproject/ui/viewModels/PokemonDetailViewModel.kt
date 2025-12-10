package com.luisperez.apiproject.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisperez.apiproject.data.model.PokemonBasicDetail
import com.luisperez.apiproject.data.remote.RetroFitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val pokemon: PokemonBasicDetail) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class PokemonDetailViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun fetchPokemonDetail(pokemonId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val pokemon = RetroFitClient.api.getPokemonDetail(pokemonId)
                _uiState.value = DetailUiState.Success(pokemon)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.message ?: "Error desconocido al cargar el detalle")
            }
        }
    }
}