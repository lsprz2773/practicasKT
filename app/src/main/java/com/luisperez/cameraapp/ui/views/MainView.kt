package com.luisperez.cameraapp.ui.views

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.luisperez.cameraapp.ui.viewModels.CameraViewModel
import java.io.File

@Composable
fun MainView(viewModel: CameraViewModel = viewModel(),
             context: Context = LocalContext.current
){
    val photoUri = remember {
        val file = File(context.cacheDir, "photo_${ System.currentTimeMillis()}.jpg")
        FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        isGranted -> viewModel.setCameraPermission(isGranted)
    }

    val cameraLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
        success -> if (success){ viewModel.setImageUri(photoUri)}
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                when{
                    viewModel.hasCameraPermission.value -> {
                        cameraLauncher.launch(photoUri)
                    } else -> {
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
                }
            }
        ) {
            Text("Abrir camara")
        }

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.imageUri.value?.let {
                uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Foto capturada",
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

    }
}
