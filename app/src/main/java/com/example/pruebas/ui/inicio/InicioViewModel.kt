package com.example.pruebas.ui.inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InicioViewModel : ViewModel() {

    private val _qrImage = MutableLiveData<Int>().apply {
    }
    val qrImage: LiveData<Int> = _qrImage
}
