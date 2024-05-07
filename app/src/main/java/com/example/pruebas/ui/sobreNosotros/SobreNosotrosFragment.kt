package com.example.pruebas.ui.sobreNosotros

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.pruebas.R

class SobreNosotrosFragment : Fragment() {

    companion object {
        fun newInstance() = SobreNosotrosFragment()
    }

    private val viewModel: SobreNosotrosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_sobre_nosotros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSobreNosotros: ImageView = view.findViewById(R.id.imageSobreNosotros)
        // Ahora puedes usar imageSobreNosotros para manipular la imagen
    }
}
