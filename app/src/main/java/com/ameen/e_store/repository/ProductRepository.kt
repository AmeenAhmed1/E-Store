package com.ameen.e_store.repository

import com.ameen.e_store.data.DummyData
import com.ameen.e_store.data.model.ProductModel

class ProductRepository() {

    fun getCategories() = DummyData.getCategoriesData()

    fun getProducts() = DummyData.getProducts()

    fun getBrands() = DummyData.getBrands()

    fun getUser() = DummyData.getUserData()

    fun getCart(productCart: MutableList<ProductModel>) = DummyData.getCartData(productCart)
}