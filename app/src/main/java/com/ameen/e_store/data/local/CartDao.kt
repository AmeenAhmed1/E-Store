package com.ameen.e_store.data.local

import androidx.room.*
import com.ameen.e_store.data.model.*
import retrofit2.http.GET

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartProduct(product: ProductModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrands(brands: List<BrandModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoriesModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserModel)

    @Query("SELECT * FROM review WHERE reviewProductId = :id")
    suspend fun getProductReviews(id: Int): List<ReviewModel>
}