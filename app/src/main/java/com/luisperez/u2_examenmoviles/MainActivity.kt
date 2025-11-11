package com.luisperez.u2_examenmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.luisperez.u2_examenmoviles.dataStore.AppThemeStore
import com.luisperez.u2_examenmoviles.navigation.Navigation
import com.luisperez.u2_examenmoviles.ui.theme.U2examenMovilesTheme
import com.luisperez.u2_examenmoviles.viewModels.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: FormViewModel by viewModels()
        setContent {
            val darkModeStore = AppThemeStore(this)
            val darkMode = darkModeStore.getDarkMode.collectAsState(initial = false)
            U2examenMovilesTheme(
                darkTheme = darkMode.value
            ) {
                    Navigation(viewModel, darkModeStore, darkMode.value)
            }
        }
    }
}
