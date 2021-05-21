package com.ameen.e_store.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoriesModel(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Int,
    val categoryIcon: Int,
    val categoryTitle: String
)