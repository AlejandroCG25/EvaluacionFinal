package com.alejandro.evaluacioncontinua03.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alejandro.evaluacioncontinua03.R
import com.alejandro.evaluacioncontinua03.databinding.FragmentListaBinding
import com.alejandro.evaluacioncontinua03.model.Producto
import com.alejandro.evaluacioncontinua03.viewmodels.ProductoListViewModel

class ListaFragment : Fragment() {
    private lateinit var binding: FragmentListaBinding
    private lateinit var viewModel: ProductoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductoListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVProductoAdapter(listOf()){producto->
            val destination = ListaFragmentDirections.actionListaFragmentToDetalleFragment(producto)
            findNavController().navigate(destination)
        }
        binding.rvAmiiboList.adapter = adapter
        viewModel.products.observe(requireActivity()){
            adapter.products = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getProductsFromService()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterProducts(s.toString(), adapter)
            }
        })

    }
    private fun filterProducts(query: String, adapter: RVProductoAdapter) {
        val originalList = viewModel.products.value ?: emptyList()
        val filteredList = mutableListOf<Producto>()

        for (product in originalList) {
            if (product.name.contains(query, ignoreCase = true)) {
                filteredList.add(product)
            }
        }

        adapter.products = filteredList
        adapter.notifyDataSetChanged()
    }


}