package com.alejandro.evaluacioncontinua03.data.repository

import com.alejandro.evaluacioncontinua03.data.retrofit.RetrofitHelper
import com.alejandro.evaluacioncontinua03.model.Producto

class ProductoRepository {
    suspend fun getProducts(): List<Producto>{
        val response =RetrofitHelper.productoService.getAllProducts()
        return response.amiibo
    }
}