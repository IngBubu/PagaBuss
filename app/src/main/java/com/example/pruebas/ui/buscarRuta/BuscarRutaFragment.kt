package com.example.pruebas.ui.buscarRuta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pruebas.databinding.FragmentBuscarrutaBinding

class BuscarRutaFragment : Fragment() {

    private var _binding: FragmentBuscarrutaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val buscarRutaViewModel =
            ViewModelProvider(this).get(BuscarRutaViewModel::class.java)

        _binding = FragmentBuscarrutaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Cambio aquí para trabajar con ImageView
        val imageView = binding.mapaPrueba
        // Aquí puedes establecer cualquier lógica relacionada con la ImageView

        // No olvides manejar la observación del ViewModel
        buscarRutaViewModel.mapaPrueba.observe(viewLifecycleOwner) {
            // Puedes realizar cualquier acción aquí basada en los cambios del ViewModel
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}