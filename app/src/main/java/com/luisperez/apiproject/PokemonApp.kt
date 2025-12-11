package com.luisperez.apiproject

import android.app.Application
import com.luisperez.apiproject.data.local.PokemonDatabase

class PokemonApp: Application() {
    val database by lazy { PokemonDatabase.getDatabase(this) }
}