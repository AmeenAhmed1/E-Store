package com.ameen.e_store.data.model

import java.io.Serializable

data class ProductModel(
    val productId: Int,
    val productImage: Int,
    val productTitle: String,
    val productDescription: String,
    val productPrice: Int,
    val productCategory: CategoriesModel? = null,
    val productBrand: BrandModel? = null,
    val productStateNew: Boolean = false,
    val productReviews: List<ReviewModel>? = null
) : Serializable
