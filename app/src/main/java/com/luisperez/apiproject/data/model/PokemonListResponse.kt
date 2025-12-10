package com.luisperez.apiproject.data.model

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
){
    fun extractId(): Int {
        return url.trimEnd('/').split("/").last().toIntOrNull() ?: 0
    }

    fun getImageUrl(): String {
        val id = extractId()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}