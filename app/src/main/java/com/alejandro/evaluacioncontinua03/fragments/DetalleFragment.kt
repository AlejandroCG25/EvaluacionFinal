package com.alejandro.evaluacioncontinua03.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.alejandro.evaluacioncontinua03.databinding.FragmentDetalleBinding
import com.alejandro.evaluacioncontinua03.model.Producto
import com.alejandro.evaluacioncontinua03.viewmodels.ProductoDetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar


class DetalleFragment : Fragment() {
    private lateinit var binding: FragmentDetalleBinding
    private val args: DetalleFragmentArgs by navArgs()
    private lateinit var producto: Producto
    private lateinit var viewModel: ProductoDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        producto = args.producto
        viewModel = ViewModelProvider(requireActivity())[ProductoDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.root.context).load(producto.image).fitCenter().into(binding.imgProduct)
        binding.txtamiibo.text = producto.name
        binding.txtjuego.text = "Juego: "+ producto.gameSeries
        binding.txtserie.text = "Serie de Amiibo: "+producto.amiiboSeries
        binding.txtdescripcion.text = "Personaje: "+producto.character

        if (producto.isFavorite){
            binding.btnAddfav.text = "Eliminar de Favoritos"
        }

        binding.btnAddfav.setOnClickListener {
            if (producto.isFavorite) {
                producto.isFavorite = false
                viewModel.removeFavorites(producto)
                Toast.makeText(requireContext(), "Amiibo eliminado de favoritos", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addFavorites(producto)
            }
        }

        viewModel.showDuplicateMessage.observe(viewLifecycleOwner, { show ->
            if (show) {
                Toast.makeText(requireContext(), "El amiibo ya se encuentra en favoritos", Toast.LENGTH_SHORT).show()
                viewModel.setShowDuplicateMessage(false) // Establece el valor a false
            }
        })
    }

}