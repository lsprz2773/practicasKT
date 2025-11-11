package com.luisperez.u2_examenmoviles.repository

import com.luisperez.u2_examenmoviles.room.PersonDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonRepository @Inject constructor(private val personDatabaseDao: PersonDatabaseDao) {

    suspend fun addPerson(person: com.luisperez.u2_examenmoviles.models.Person) = personDatabaseDao.createPerson(person)
    fun getPersonById(id:Long) = personDatabaseDao.getPersonById(id)
    suspend fun updatePerson(person: com.luisperez.u2_examenmoviles.models.Person) = personDatabaseDao.updatePerson(person)
    suspend fun deletePerson(person: com.luisperez.u2_examenmoviles.models.Person) = personDatabaseDao.deletePerson(person)
    fun getAllPersons() = personDatabaseDao.getPersons().flowOn(Dispatchers.IO).conflate()

}