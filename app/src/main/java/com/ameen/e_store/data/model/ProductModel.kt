package com.ameen.e_store.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.io.Serializable

@Entity(
    tableName = "cart_product",
    foreignKeys = [
        ForeignKey(
            entity = CategoriesModel::class,
            parentColumns = ["categoryId"],
            childColumns = ["productCategory"]
        ),
        ForeignKey(
            entity = BrandModel::class,
            parentColumns = ["brandId"],
            childColumns = ["productBrand"]
        ),
//        ForeignKey(
//            entity = ReviewModel::class,
//            parentColumns = ["reviewId"],
//            childColumns = ["productReviews"],
//            onDelete = CASCADE
//        ),
    ]
)
data class ProductModel(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val productImage: Int,
    val productTitle: String,
    val productDescription: String,
    val productPrice: Int,
    val productCategory: Int? = null,
    val productBrand: Int? = null,
    val productStateNew: Boolean = false,
    //val productReviews: Int? = null,
    var productCountInCart: Int = 0
) : Serializable
