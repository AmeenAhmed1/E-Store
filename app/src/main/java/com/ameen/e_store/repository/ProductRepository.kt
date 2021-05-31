package com.ameen.e_store.repository

import com.ameen.e_store.data.DummyData
import com.ameen.e_store.data.local.CartDatabase
import com.ameen.e_store.data.model.*
import javax.inject.Inject

class ProductRepository @Inject constructor(
    val db: CartDatabase
) {

    fun getCategories() = DummyData.getCategoriesData()

    fun getProducts() = DummyData.getProducts()

    fun getBrands() = DummyData.getBrands()

    fun getUser() = DummyData.getUserData()

    fun getCart(productCart: MutableList<ProductModel>) = DummyData.getCartData(productCart)

    //Room functions
    suspend fun saveCartItem(product: ProductModel) =
        db.getCartDao().insertCartProduct(product)

    suspend fun insertCategories(categories: List<CategoriesModel>) =
        db.getCartDao().insertCategories(categories)

    suspend fun insertBrands(brands: List<BrandModel>) =
        db.getCartDao().insertBrands(brands)

    suspend fun insertReviews(reviews: List<ReviewModel>) =
        db.getCartDao().insertReviews(reviews)

    suspend fun insertUser(user: UserModel) =
        db.getCartDao().insertUser(user)
}