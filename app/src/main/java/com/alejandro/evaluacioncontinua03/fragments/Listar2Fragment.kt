package com.alejandro.evaluacioncontinua03.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejandro.evaluacioncontinua03.R
import com.alejandro.evaluacioncontinua03.databinding.FragmentListar2Binding
import com.alejandro.evaluacioncontinua03.model.FirestoreProducto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Listar2Fragment : Fragment() {
    private lateinit var binding: FragmentListar2Binding
    private lateinit var adapter: AmiiboAdapter
    private val productosList = mutableListOf<FirestoreProducto>() // Cambiar a FirestoreProducto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListar2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AmiiboAdapter(productosList)
        binding.rvFirestore.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFirestore.adapter = adapter

        val firestore = Firebase.firestore
        firestore.collection("producto").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                val amiibo = document.toObject(FirestoreProducto::class.java)
                productosList.add(amiibo)
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener {

        }
    }
}