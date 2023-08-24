package com.alejandro.evaluacioncontinua03.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.evaluacioncontinua03.databinding.ItemAmiiboBinding
import com.alejandro.evaluacioncontinua03.model.FirestoreProducto
import com.bumptech.glide.Glide

class AmiiboAdapter(private val amiiboList: List<FirestoreProducto>) : RecyclerView.Adapter<AmiiboAdapter.AmiiboViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder {
        val binding = ItemAmiiboBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AmiiboViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        val amiibo = amiiboList[position]
        holder.bind(amiibo)
    }

    override fun getItemCount(): Int {
        return amiiboList.size
    }

    inner class AmiiboViewHolder(private val binding: ItemAmiiboBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(productos: FirestoreProducto) {
            Glide.with(binding.root.context).load(productos.image).fitCenter().into(binding.txtImagen)
            binding.txtNombre.text = productos.name
            binding.txtJuego.text = "Juego: "+ productos.gameSeries
            binding.txtSerie.text = "Serie de Amiibo: "+productos.amiiboSeries
            binding.txtPersonaje.text=productos.character

            // También puedes configurar un OnClickListener para los elementos del RecyclerView si lo deseas
        }
    }
}