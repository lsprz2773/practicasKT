package com.luisperez.cameraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.luisperez.cameraapp.ui.theme.CameraAppTheme
import com.luisperez.cameraapp.ui.viewModels.CameraViewModel
import com.luisperez.cameraapp.ui.views.MainView
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val cameraViewModel by viewModels<CameraViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraAppTheme {
                MainView(viewModel = cameraViewModel)
            }
        }
    }
}
