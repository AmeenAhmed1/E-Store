package com.ameen.e_store.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand")
data class BrandModel(
    @PrimaryKey(autoGenerate = true)
    val brandId: Int,
    val brandImage: Int,
    val branTitle: String,
    val brandProductsNumber: Int
)
