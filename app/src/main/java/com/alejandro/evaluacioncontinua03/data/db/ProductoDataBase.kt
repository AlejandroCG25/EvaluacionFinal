package com.alejandro.evaluacioncontinua03.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alejandro.evaluacioncontinua03.model.Producto

@Database(entities = [Producto::class], version = 1)
abstract class ProductoDataBase: RoomDatabase() {
    abstract fun productoDao(): ProductoDao

    companion object{
        @Volatile
        private var instance: ProductoDataBase? = null
        fun getDatabase(context: Context): ProductoDataBase{
            val tempInstance= instance
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val _instance = Room.databaseBuilder(context.applicationContext, ProductoDataBase::class.java,"productodb").build()
                instance = _instance
                return _instance
            }
        }
    }
}