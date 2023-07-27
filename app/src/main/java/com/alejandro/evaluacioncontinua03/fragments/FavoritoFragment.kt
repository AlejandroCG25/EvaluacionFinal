package com.alejandro.evaluacioncontinua03.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alejandro.evaluacioncontinua03.R
import com.alejandro.evaluacioncontinua03.databinding.FragmentFavoritoBinding

class FavoritoFragment : Fragment() {

    private lateinit var binding: FragmentFavoritoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritoBinding.inflate(inflater,container,false)
        return binding.root
    }

}