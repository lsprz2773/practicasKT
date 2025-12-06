package com.luisperez.cameraapp.ui.views

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luisperez.cameraapp.ui.viewModels.CameraViewModel

@Composable
fun MainView(viewModel: CameraViewModel = viewModel(),
             context: Context = LocalContext.current
){

}

@Composable
fun MainViewContent(){}