package com.luisperez.coroutinesappa.viewModels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesViewModel: ViewModel() {
    val result = mutableStateOf("")
    val isLoading =mutableStateOf(false)

    fun functionOne(){
        viewModelScope.launch {
            isLoading.value = true
            result.value = withContext(Dispatchers.IO){
                delay(5000)
                "Funcion uno cargada"
            }
            isLoading.value = false
        }
    }

    fun functionTwo(){
        viewModelScope.launch {
            isLoading.value = true
            result.value = withContext(Dispatchers.IO){
                delay(5000)
                "Funcion dos cargada"
            }
            isLoading.value = false
        }
    }

    fun functionThree(){
        viewModelScope.launch {
            isLoading.value = true
            result.value = withContext(Dispatchers.IO){
                delay(5000)
                "Funcion tres cargada"
            }
            isLoading.value = false
        }
    }
}
