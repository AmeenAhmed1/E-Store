package com.ameen.e_store.data.model

data class ProductModel(
    val productImage: Int,
    val productTitle: String,
    val productDescription: String,
    val productPrice: Int,
    val productCategory: CategoriesModel? = null,
    val productBrand: BrandModel? = null,
    val productStateNew: Boolean = false,
)
