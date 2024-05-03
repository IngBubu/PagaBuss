package com.example.pruebas.ui.inicio

// InicioViewModel.kt
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebas.GenerateQRCode
import com.google.firebase.auth.FirebaseAuth

class InicioViewModel : ViewModel() {

    private val _qrImage = MutableLiveData<Bitmap?>()
    val qrImage: MutableLiveData<Bitmap?> get() = _qrImage

    fun generateQRCode() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val qrCodeGenerator = GenerateQRCode()
        val qrCodeBitmap = qrCodeGenerator.generateQRCode(userId)
        if (qrCodeBitmap != null) {
            _qrImage.value = qrCodeBitmap
        }
    }
}