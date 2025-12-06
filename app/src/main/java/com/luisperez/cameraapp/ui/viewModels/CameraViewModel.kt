package com.luisperez.cameraapp.ui.viewModels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class CameraViewModel: ViewModel() {
    private val _imageUri = mutableStateOf<Uri?>(null)
    val imageUri: State<Uri?> = _imageUri

    private val _hasCameraPermission = mutableStateOf(false)
    val hasCameraPermission: State<Boolean> = _hasCameraPermission

    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
    }

    fun setCameraPermission(granted: Boolean) {
        _hasCameraPermission.value = granted
    }
}