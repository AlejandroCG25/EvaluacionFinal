package com.alejandro.evaluacioncontinua03.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.evaluacioncontinua03.databinding.ItemProductoBinding
import com.alejandro.evaluacioncontinua03.model.Producto
import com.bumptech.glide.Glide

class RVProductoAdapter (var products:List<Producto>, val onProductClick: (Producto)-> Unit): RecyclerView.Adapter<ProductVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val binding= ItemProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductVH(binding, onProductClick)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.bind(products[position])
    }

}

class ProductVH(private val binding: ItemProductoBinding, val onProductClick: (Producto)-> Unit): RecyclerView.ViewHolder(binding.root){
    fun bind(productos: Producto) {

        Glide.with(binding.root.context).load(productos.image).fitCenter().into(binding.txtImagen)
        binding.txtNombre.text = productos.name
        binding.txtJuego.text = "Juego: "+ productos.gameSeries
        binding.txtSerie.text = "Serie de Amiibo: "+productos.amiiboSeries

        binding.root.setOnClickListener {
            onProductClick(productos)
        }


    }

}