package com.ameen.e_store.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "review",
    foreignKeys = [
        ForeignKey(
            entity = UserModel::class,
            parentColumns = ["userId"],
            childColumns = ["reviewUserId"]
        ),
        ForeignKey(
            entity = ProductModel::class,
            parentColumns = ["productId"],
            childColumns = ["reviewProductId"]
        )
    ]
)
data class ReviewModel(
    @PrimaryKey(autoGenerate = true)
    val reviewId: Int? = null,
    val reviewUserId: Int,
    val reviewBody: String,
    val reviewRating: Float,
    val reviewProductId: Int
)
