package com.alejandro.evaluacioncontinua03.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alejandro.evaluacioncontinua03.model.Producto

@Dao
interface ProductoDao {

    @Insert
    suspend fun addProductToFavorite(producto: Producto)

    @Query("Select * from producto")
    suspend fun getFavorites(): List<Producto>

    @Delete
    suspend fun removeFavorite(producto: Producto)

    @Query("SELECT * FROM producto WHERE tail = :productId")
    suspend fun getFavoriteById(productId: String): Producto?
}