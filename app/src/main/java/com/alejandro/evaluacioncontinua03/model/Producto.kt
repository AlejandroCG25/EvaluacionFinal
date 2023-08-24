package com.alejandro.evaluacioncontinua03.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "producto")
@Parcelize
data class Producto(
    @PrimaryKey
    val tail:String,
    val name:String,
    val gameSeries:String,
    val image:String,
    val amiiboSeries:String,
    val character:String,
    var isFavorite:Boolean = false
): Parcelable
/*fun getData() : List<Producto>{
    return listOf(
        Producto("Dr. Mario","Super Mario","https://raw.githubusercontent.com/N3evin/AmiiboAPI/master/images/icon_00000100-00190002.png","Super Smash Bros."),
        Producto("Pink Yarn Yoshi","Super Mario","https://raw.githubusercontent.com/N3evin/AmiiboAPI/master/images/icon_00030102-00420302.png","Yoshi's Woolly World"),
        Producto("Donkey Kong","Super Mario","https://raw.githubusercontent.com/N3evin/AmiiboAPI/master/images/icon_00080000-02640102.png","Super Mario Bros."),
    )

}*/