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

class ProductoDetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ProductoRepository
    private val _showDuplicateMessage = MutableLiveData<Boolean>()
    val showDuplicateMessage: LiveData<Boolean> = _showDuplicateMessage

    init {
        val db = ProductoDataBase.getDatabase(application)
        repository = ProductoRepository(db)
    }

    fun setShowDuplicateMessage(value: Boolean) {
        _showDuplicateMessage.value = value
    }

    fun removeFavorites(producto: Producto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeProductoFromFavorites(producto)
        }
    }

    fun addFavorites(producto: Producto) {
        viewModelScope.launch(Dispatchers.IO) {
            val isAdded = repository.addProductoToFavorites(producto)
            if (!isAdded) {
                _showDuplicateMessage.postValue(true)
            }
        }
    }
}