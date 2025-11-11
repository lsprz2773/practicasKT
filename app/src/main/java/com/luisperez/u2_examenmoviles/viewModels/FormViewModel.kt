package com.luisperez.u2_examenmoviles.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisperez.u2_examenmoviles.models.Person
import com.luisperez.u2_examenmoviles.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: PersonRepository): ViewModel() {
    private val _personList = MutableStateFlow<List<Person>>(emptyList())
    val personList = _personList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllPersons().collect{ item ->
                if (item.isEmpty()){
                    _personList.value = emptyList<Person>()
                }else{
                    _personList.value = item
                }
            }
        }
    }

    fun addPerson(person: Person) = viewModelScope.launch { repository.addPerson(person) }
    fun updatePerson(person: Person) = viewModelScope.launch { repository.updatePerson(person) }
    fun deletePerson(person: Person) = viewModelScope.launch { repository.deletePerson(person) }

}