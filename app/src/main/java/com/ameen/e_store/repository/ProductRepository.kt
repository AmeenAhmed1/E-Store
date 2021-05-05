package com.ameen.e_store.repository

import com.ameen.e_store.data.DummyData

class ProductRepository() {

    fun getCategories() = DummyData.getCategoriesData()

    fun getProducts() = DummyData.getProducts()

}