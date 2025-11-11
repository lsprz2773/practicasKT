package com.luisperez.u2_examenmoviles.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisperez.u2_examenmoviles.dataStore.AppThemeStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeThemeView(navController: NavController, appThemeStore: AppThemeStore, themeValue: Boolean){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Tema de la app")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        ChangeThemeViewContent(it, appThemeStore, themeValue)
    }
}

@Composable
fun ChangeThemeViewContent(paddingValues: PaddingValues, appThemeStore: AppThemeStore, themeValue: Boolean){
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .width(300.dp),
            onClick = {
                scope.launch {
                    if (themeValue){
                        appThemeStore.saveDarkMode(false)
                    } else {
                        appThemeStore.saveDarkMode(true)
                    }
                }
            }
        ) {
            Text("Cambiar tema")
        }
    }
}