package com.alejandro.evaluacioncontinua03.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alejandro.evaluacioncontinua03.data.db.ProductoDataBase
import com.alejandro.evaluacioncontinua03.data.repository.ProductoRepository
import com.alejandro.evaluacioncontinua03.model.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoFavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ProductoRepository
    private var _favorites: MutableLiveData<List<Producto>> = MutableLiveData()
    var favorites:LiveData<List<Producto>> = _favorites

    init {
        val db = ProductoDataBase.getDatabase(application)
        repository = ProductoRepository(db)
    }

    fun getFavoretes(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }
}