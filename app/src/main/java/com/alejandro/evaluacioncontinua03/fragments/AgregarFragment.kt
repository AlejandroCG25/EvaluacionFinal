package com.alejandro.evaluacioncontinua03.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.alejandro.evaluacioncontinua03.R
import com.alejandro.evaluacioncontinua03.databinding.FragmentAgregarBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AgregarFragment : Fragment() {
    private lateinit var binding: FragmentAgregarBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgregarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            val nombre = binding.tilNombre.editText?.text.toString()
            val serie = binding.tilSerie.editText?.text.toString()
            val juego = binding.tilJuego.editText?.text.toString()
            val personaje = binding.tilPersonaje.editText?.text.toString()
            val imagen = binding.tilImagen.editText?.text.toString()

            if (nombre.isNotEmpty() && serie.isNotEmpty() && juego.isNotEmpty() && personaje.isNotEmpty() && imagen.isNotEmpty()){
                addToFirestore(nombre,serie,juego,personaje,imagen)
                binding.tilNombre.editText?.setText("")
                binding.tilSerie.editText?.setText("")
                binding.tilJuego.editText?.setText("")
                binding.tilPersonaje.editText?.setText("")
                binding.tilImagen.editText?.setText("")
            }else{
                Toast.makeText(requireContext(),"Todos los campos deben estar llenados!!!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnListar.setOnClickListener {
            // Navegar al fragment Listar2Fragment
            val navController = findNavController()
            navController.navigate(R.id.action_agregarFragment_to_listar2Fragment)
        }
    }

    private fun addToFirestore(nombre: String, serie: String, juego: String, personaje: String, imagen: String) {
        val newProduct = hashMapOf<String, Any>(
            "name" to nombre,
            "amiiboSeries" to serie,
            "gameSeries" to juego,
            "character" to personaje,
            "image" to imagen
        )
        firestore.collection("producto").add(newProduct).addOnSuccessListener {
            Toast.makeText(requireContext(),"Amiibo agregado con id: ${it.id} ",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(),"Ocurrio un error!",Toast.LENGTH_SHORT).show()
        }

    }


}