package com.alejandro.evaluacioncontinua03.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://www.amiiboapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val productoService:ProductoService = retrofit.create(ProductoService::class.java)

}