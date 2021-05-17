package com.ameen.e_store.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.e_store.data.model.BrandModel
import com.ameen.e_store.data.model.CategoriesModel
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.data.model.UserModel
import com.ameen.e_store.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val categoriesData: MutableLiveData<MutableList<CategoriesModel>> = MutableLiveData()
    val productsData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()
    val brandsData: MutableLiveData<MutableList<BrandModel>> = MutableLiveData()
    val recommendedData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()
    val userData: MutableLiveData<UserModel> = MutableLiveData()

    init {
        getCategories()
        getProducts()
        getBrands()
        getUserData()
    }

    fun getCategories() = viewModelScope.launch {
        val result = productRepository.getCategories()
        categoriesData.postValue(result)
    }

    fun getProducts() = viewModelScope.launch {
        val result = productRepository.getProducts()
        productsData.postValue(
            result.subList(
                0,
                2
            )
        ) //Send the first 2 elements as best selling items
        recommendedData.postValue(result)
    }

    fun getBrands() = viewModelScope.launch {
        val result = productRepository.getBrands()
        brandsData.postValue(result)
    }

    fun getUserData() = viewModelScope.launch {
        val result = productRepository.getUser()
        userData.postValue(result)
    }
}
