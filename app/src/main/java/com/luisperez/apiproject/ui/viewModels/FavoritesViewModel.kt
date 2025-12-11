package com.luisperez.apiproject.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luisperez.apiproject.PokemonApp
import com.luisperez.apiproject.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application): AndroidViewModel(application) {
    private val pokemonDao = (application as PokemonApp).database.pokemonDao()

    val favoritesList: StateFlow<List<PokemonEntity>> = pokemonDao.getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavorite(pokemonId: Int) {
        viewModelScope.launch {
            pokemonDao.deleteFavoriteById(pokemonId)
        }
    }
}