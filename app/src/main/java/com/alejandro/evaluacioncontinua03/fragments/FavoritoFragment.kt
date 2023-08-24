package com.alejandro.evaluacioncontinua03.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alejandro.evaluacioncontinua03.R
import com.alejandro.evaluacioncontinua03.databinding.FragmentFavoritoBinding
import com.alejandro.evaluacioncontinua03.viewmodels.ProductoFavoriteViewModel

class FavoritoFragment : Fragment() {

    private lateinit var binding: FragmentFavoritoBinding
    private lateinit var viewModel:ProductoFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductoFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVProductoAdapter(listOf()){producto->
            val destination = FavoritoFragmentDirections.actionFavoritoFragmentToDetalleFragment(producto)
            findNavController().navigate(destination)
        }
        binding.rvFavorites.adapter = adapter
        viewModel.favorites.observe(requireActivity()){
            adapter.products = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavoretes()
    }

}