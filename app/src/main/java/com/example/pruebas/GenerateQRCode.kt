package com.example.pruebas

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
class GenerateQRCode {
    fun generateQRCode(text: String): Bitmap? {
        val width = 1500
        val height = 1500
        val bitMatrix: BitMatrix
        try{
            bitMatrix = MultiFormatWriter().encode(
            text,
            BarcodeFormat.QR_CODE,
            width, height, null
        )
        } catch (Illegalargumentexception: IllegalArgumentException) {
            return null
        }
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
    }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }
}