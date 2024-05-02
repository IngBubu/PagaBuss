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

        // Cambio aquí para trabajar con ImageView
        val imageView = binding.QrPrueba
        // Aquí puedes establecer cualquier lógica relacionada con la ImageView

        // No olvides manejar la observación del ViewModel
        inicioViewModel.qrImage.observe(viewLifecycleOwner) {
            // Puedes realizar cualquier acción aquí basada en los cambios del ViewModel
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
