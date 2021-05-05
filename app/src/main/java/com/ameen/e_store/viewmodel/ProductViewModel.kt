package com.ameen.e_store.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.e_store.data.DummyData
import com.ameen.e_store.data.model.CategoriesModel
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel() : ViewModel() {

    val categoriesData: MutableLiveData<MutableList<CategoriesModel>> = MutableLiveData()
    val productsData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()

    private val productRepository = ProductRepository()

    init {
        getCategories()
        getProducts()
    }

    fun getCategories() = viewModelScope.launch {
        val result = productRepository.getCategories()
        categoriesData.postValue(result)
    }

    fun getProducts() = viewModelScope.launch {
        val result = productRepository.getProducts()
        productsData.postValue(result)
    }
}