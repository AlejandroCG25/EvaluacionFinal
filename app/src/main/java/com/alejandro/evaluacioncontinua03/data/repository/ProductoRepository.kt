package com.alejandro.evaluacioncontinua03.data.repository

import com.alejandro.evaluacioncontinua03.data.db.ProductoDao
import com.alejandro.evaluacioncontinua03.data.db.ProductoDataBase
import com.alejandro.evaluacioncontinua03.data.retrofit.RetrofitHelper
import com.alejandro.evaluacioncontinua03.model.Producto

class ProductoRepository(val db: ProductoDataBase? = null) {
    private val dao:ProductoDao? = db?.productoDao()
    suspend fun getProducts(): List<Producto>{
        val response =RetrofitHelper.productoService.getAllProducts()
        return response.amiibo
    }

    suspend fun getFavorites(): List<Producto>{
        dao?.let {
            return dao.getFavorites()
        }?: kotlin.run {
            return listOf()
        }
    }


    suspend fun addProductoToFavorites(producto: Producto): Boolean {
        val existingFavorite = dao?.getFavoriteById(producto.tail)

        if (existingFavorite == null) {
            dao?.addProductToFavorite(producto)
            return true // Producto agregado con éxito
        } else {
            return false // Producto ya está en favoritos
        }
    }

    suspend fun removeProductoFromFavorites(producto: Producto) {
        dao?.let {
            dao.removeFavorite(producto)
        }
    }

}