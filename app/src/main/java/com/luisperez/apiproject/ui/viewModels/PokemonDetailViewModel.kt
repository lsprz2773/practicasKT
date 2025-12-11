package com.luisperez.apiproject.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisperez.apiproject.PokemonApp
import com.luisperez.apiproject.data.local.entity.PokemonEntity
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

class PokemonDetailViewModel(application: Application): AndroidViewModel(application){
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    private val pokemonDao = (application as PokemonApp).database.pokemonDao()

    fun fetchPokemonDetail(pokemonId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val pokemon = RetroFitClient.api.getPokemonDetail(pokemonId)
                _uiState.value = DetailUiState.Success(pokemon)

                checkIfFavorite(pokemon.id)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.message ?: "Error desconocido al cargar el detalle")
            }
        }
    }

    private fun checkIfFavorite(id: Int){
        viewModelScope.launch {
            _isFavorite.value = pokemonDao.isFavorite(id)
        }
    }

    fun toggleFavorite(pokemon: PokemonBasicDetail) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                pokemonDao.deleteFavoriteById(pokemon.id)
                _isFavorite.value = false
            } else {
                val imageUrl = pokemon.sprites.other?.officialArtwork?.frontHd
                    ?: pokemon.sprites.pixelFront
                    ?: ""

                val entity = PokemonEntity(
                    id = pokemon.id,
                    name = pokemon.name,
                    imageUrl = imageUrl
                )
                pokemonDao.insertFavorite(entity)
                _isFavorite.value = true
            }
        }
    }
}