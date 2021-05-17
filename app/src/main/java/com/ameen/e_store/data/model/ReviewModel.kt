package com.ameen.e_store.data.model

data class ReviewModel(
    val reviewUserId: UserModel,
    val reviewBody: String,
    val reviewRating: Float
)
