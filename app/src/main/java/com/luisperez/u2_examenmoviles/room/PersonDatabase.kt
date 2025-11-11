package com.luisperez.u2_examenmoviles.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luisperez.u2_examenmoviles.models.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDatabaseDao
}