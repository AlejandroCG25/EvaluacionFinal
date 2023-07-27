package com.alejandro.evaluacioncontinua03.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandro.evaluacioncontinua03.data.repository.ProductoRepository
import com.alejandro.evaluacioncontinua03.model.Producto
import com.alejandro.evaluacioncontinua03.model.getData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoListViewModel: ViewModel() {
    val products: MutableLiveData<List<Producto>> = MutableLiveData<List<Producto>>()
    val repository = ProductoRepository()

    fun getAllProducts(){
        val productList = getData()
        products.value = productList
    }

    fun getProductsFromService(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getProducts()
            products.postValue(response)
        }
    }
}