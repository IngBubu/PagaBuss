// InicioFragment.kt
package com.example.pruebas.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pruebas.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inicioViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imageView = binding.QrPrueba

        inicioViewModel.qrImage.observe(viewLifecycleOwner) { qrImage ->
            imageView.setImageBitmap(qrImage)
        }

        inicioViewModel.generateQRCode()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}