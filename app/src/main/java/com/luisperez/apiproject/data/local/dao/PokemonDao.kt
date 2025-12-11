package com.luisperez.apiproject.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luisperez.apiproject.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM favorites_table ORDER BY savedAt DESC")
    fun getAllFavorites(): Flow<List<PokemonEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_table WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokemon: PokemonEntity)

    @Delete
    suspend fun deleteFavorite(pokemon: PokemonEntity)

    @Query("DELETE FROM favorites_table WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)
}