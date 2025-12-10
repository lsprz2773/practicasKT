package com.luisperez.apiproject.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pokemones") }
            )
        }
    ) { innerPadding ->
        HomeViewContent(innerPadding)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues){

}