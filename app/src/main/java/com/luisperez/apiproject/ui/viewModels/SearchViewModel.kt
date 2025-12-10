package com.luisperez.apiproject.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisperez.apiproject.data.remote.RetroFitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Error(val message: String) : SearchUiState()
    data class Success(val pokemonName: String) : SearchUiState()
}

class SearchViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun searchPokemon() {
        val currentQuery = _query.value.trim().lowercase()
        if (currentQuery.isEmpty()) return

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            try {

                RetroFitClient.api.getPokemonDetail(currentQuery)

                _uiState.value = SearchUiState.Success(currentQuery)
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error("No se encontró a '$currentQuery'")
            }
        }
    }

    fun resetState() {
        _uiState.value = SearchUiState.Idle
        _query.value = ""
    }
}