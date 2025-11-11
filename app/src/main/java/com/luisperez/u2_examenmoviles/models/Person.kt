package com.luisperez.u2_examenmoviles.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "personId")
    val personId: String,
)
