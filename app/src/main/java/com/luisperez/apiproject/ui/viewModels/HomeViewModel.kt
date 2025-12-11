package com.luisperez.apiproject.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luisperez.apiproject.PokemonApp
import com.luisperez.apiproject.data.local.entity.PokemonEntity
import com.luisperez.apiproject.data.model.PokemonResult
import com.luisperez.apiproject.data.remote.RetroFitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _favoriteIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteIds = _favoriteIds.asStateFlow()

    private val pokemonDao = (application as PokemonApp).database.pokemonDao()

    private var currentOffset = 0
    private val limit = 20
    private var isLoadingMore = false
    private var isLastPage = false

    init {
        fetchPokemons()
        observeFavorites()
    }

    fun fetchPokemons(){
        if (isLoadingMore || isLastPage) return

        viewModelScope.launch {
            try {
                if (currentOffset == 0) {
                    _uiState.value = HomeUiState.Loading
                }

                val response = RetroFitClient.api.getPokemonList(limit = limit, offset = currentOffset)
                if (response.results.isNotEmpty()) {
                    val currentList = if (_uiState.value is HomeUiState.Success) {
                        (_uiState.value as HomeUiState.Success).pokemons
                    } else {
                        emptyList()
                    }

                    val newList = currentList + response.results

                    _uiState.value = HomeUiState.Success(newList)

                    currentOffset += limit
                } else {
                    isLastPage = true
                }
            } catch (e: Exception) {
                if (currentOffset == 0) {
                    _uiState.value = HomeUiState.Error(e.message ?: "Error desconocido")
                }
            } finally {
                isLoadingMore = false
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            pokemonDao.getAllFavorites().collect { favoritesList ->
                _favoriteIds.value = favoritesList.map { it.id }.toSet()
            }
        }
    }

    fun toggleFavorite(pokemon: PokemonResult) {
        viewModelScope.launch {
            val id = pokemon.extractId()

            if (_favoriteIds.value.contains(id)) {
                pokemonDao.deleteFavoriteById(id)
            } else {
                val entity = PokemonEntity(
                    id = id,
                    name = pokemon.name,
                    imageUrl = pokemon.getImageUrl()
                )
                pokemonDao.insertFavorite(entity)
            }
        }
    }
}

sealed class HomeUiState {
    object Loading: HomeUiState()
    data class Success(val pokemons: List<PokemonResult>): HomeUiState()
    data class Error(val message: String): HomeUiState()
}