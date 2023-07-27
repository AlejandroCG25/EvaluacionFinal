package com.alejandro.evaluacioncontinua03.data.retrofit

import com.alejandro.evaluacioncontinua03.data.response.ListProductsResponse
import retrofit2.http.GET

interface ProductoService {
    @GET("amiibo/?character")
    suspend fun getAllProducts():ListProductsResponse
}