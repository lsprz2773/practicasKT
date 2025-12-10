package com.luisperez.apiproject.data.remote

import com.luisperez.apiproject.data.model.PokemonBasicDetail
import com.luisperez.apiproject.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi{
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    @GET("pokemon/{nameOrId}")
    suspend fun getPokemonDetail(
        @Path("nameOrId") nameOrId: String
    ): PokemonBasicDetail
}