package com.luisperez.u2_examenmoviles.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.luisperez.u2_examenmoviles.models.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDatabaseDao {
    @Query("SELECT * FROM person")
    fun getPersons(): Flow<List<Person>>

    @Query("SELECT * FROM person WHERE id = :id")
    fun getPersonById(id: Long): Flow<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPerson(person: Person)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}