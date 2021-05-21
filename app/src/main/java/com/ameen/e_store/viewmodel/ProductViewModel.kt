package com.ameen.e_store.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.e_store.data.model.*
import com.ameen.e_store.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val TAG = "ProductViewModel"

    val categoriesData: MutableLiveData<MutableList<CategoriesModel>> = MutableLiveData()
    val productsData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()
    val brandsData: MutableLiveData<MutableList<BrandModel>> = MutableLiveData()
    val recommendedData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()
    val userData: MutableLiveData<UserModel> = MutableLiveData()
    val cartData: MutableLiveData<List<ProductModel>> = MutableLiveData()

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

    fun getCartData() = viewModelScope.launch {
        Log.i(TAG, "getCartData: ${recommendedData.value}")
        val result = productRepository.getCart(recommendedData.value!!)
        cartData.postValue(result)
    }


    fun saveCartItem(product: ProductModel) = viewModelScope.launch {
        productRepository.saveCartItem(product)
    }

    fun insertCategories(categories: List<CategoriesModel>) = viewModelScope.launch {
        productRepository.insertCategories(categories)
    }

    fun insertBrands(brands: List<BrandModel>) = viewModelScope.launch {
        productRepository.insertBrands(brands)
    }

    fun insertReviews(reviews: List<ReviewModel>) = viewModelScope.launch {
        productRepository.insertReviews(reviews)
    }

    fun insertUser(user: UserModel) = viewModelScope.launch {
        productRepository.insertUser(user)
    }
}
