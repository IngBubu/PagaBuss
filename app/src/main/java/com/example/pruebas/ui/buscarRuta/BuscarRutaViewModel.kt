package com.example.pruebas.ui.buscarRuta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuscarRutaViewModel : ViewModel() {

    // Cambio el LiveData para que pueda contener el recurso de imagen
    private val _mapaPrueba = MutableLiveData<Int>().apply {
        // Aquí puedes inicializar la imagen si es necesario
        // Por ejemplo: value = R.drawable.mapa_prueba
    }
    val mapaPrueba: LiveData<Int> = _mapaPrueba
}
